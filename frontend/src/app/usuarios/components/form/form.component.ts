import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserService } from '../../../usuarios/services/user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { MessageService } from "../../../core/service/message.service";
import { TipoService } from '../../../tipo/services/tipo.service';

@Component({
  selector: 'app-form-usuario',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormUsuarioComponent implements OnInit {

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public userForm = new FormGroup({
    nombre: new FormControl(''),
    apellido: new FormControl(''),
    fnacimiento: new FormControl(''),
    cargo: new FormControl(''),
    legajo: new FormControl(''),
    correo_electronico: new FormControl(''),
    ciudad: new FormControl(''),
    pais: new FormControl(''),
    provincia: new FormControl(''),
    calle: new FormControl(''),
    numero: new FormControl('')
  });
  public userId: any;
  public routeButton = "../";
  public mode = 'add';
  public section = 'Nuevo personal';
  public buttonName = 'Confirmar';
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "El usuario se ha creado correctamente";
  public dataSourceCargos: any;

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  constructor(private UserService: UserService,
    private TipoService: TipoService,
    private route: ActivatedRoute,
    private router: Router,
    private MessageService: MessageService) {}

  ngOnInit(): void {
    this.userId = this.route.snapshot.params.id;
    this.getCargos();
  }

  ngAfterViewInit(): void {
    if (this.userId) {
      this.getUser(this.userId);
    }
  }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  showSuccess() {
    this.MessageService.showSuccess({
      title: this.messageTitleSuccess,
      body: this.messageBody
    });
  }

  showError(message) {
    this.MessageService.showError({
      title: this.messageTitleError,
      body: message.errors ? message.errors[0].defaultMessage + ". campo: " + message.errors[0].field + ", Valor rechazado: " + message.errors[0].rejectedValue : message.error
    })
  }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  loadUser(user) {
    this.mode = "edit";
    this.routeButton = "../../";
    this.section = 'Editar usuario';
    this.buttonName = 'Confirmar cambios';
    this.userForm.controls.nombre.setValue(user.nombre);
    this.userForm.controls.apellido.setValue(user.apellido);
    this.userForm.controls.fnacimiento.setValue(user.fnacimiento.replace(' ', 'T'));
    this.userForm.controls.cargo.setValue(user.cargo.id);
    this.userForm.controls.legajo.setValue(user.legajo);
    this.userForm.controls.ciudad.setValue(user.direccion.ciudad);
    this.userForm.controls.pais.setValue(user.direccion.pais);
    this.userForm.controls.provincia.setValue(user.direccion.provincia);
    this.userForm.controls.calle.setValue(user.direccion.calle);
    this.userForm.controls.numero.setValue(user.direccion.numero);
    this.userForm.controls.correo_electronico.setValue(user.correo_electronico);
  }

  saveForm() {

    let request = {
      'apellido': this.userForm.controls.apellido.value,
      'cargo' : this.userForm.controls.cargo.value,
      'correo_electronico' : this.userForm.controls.correo_electronico.value,
      "direccion": {
        "calle": this.userForm.controls.calle.value,
        "ciudad": this.userForm.controls.ciudad.value,
        "id": 0,
        "numero": this.userForm.controls.numero.value,
        "pais": this.userForm.controls.pais.value,
        "provincia": this.userForm.controls.provincia.value
      },
      'fnacimiento': this.userForm.controls.fnacimiento.value,
      'legajo': this.userForm.controls.legajo.value,
      'nombre': this.userForm.controls.nombre.value
    }


    if (this.mode === 'add') {
      this.UserService.postUser(request).subscribe(
        user => {
          this.showSuccess();
          this.router.navigate(['main/personal']);
        },
        error => this.showError(error.error)
      );
    } else {
      this.UserService.updateUser(this.userId, this.userForm).subscribe(
        user => {
          this.messageBody = "El usuario se ha editado correctamente"
          this.showSuccess();
        },
        error => this.showError(error.error)
      );
    }
  }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  getUser(id) {
    this.UserService.getUser(id).pipe(first()).subscribe(
      user => {
        this.loadUser(user);
      }
    )
  }

  getCargos() {
    this.TipoService.getTipos('Cargos').subscribe(
      (data: any) => {
        this.dataSourceCargos = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.nombre
            }
          }
        );
      },
      (error) => {
        console.error(error);
      }
    );
  }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
