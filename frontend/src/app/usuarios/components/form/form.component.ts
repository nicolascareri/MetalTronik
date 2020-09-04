import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserService } from '../../../usuarios/services/user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-form-usuario',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormUsuarioComponent implements OnInit {

  public userForm: FormGroup;
  public userId: any;
  public mode = 'add';

  constructor(private UserService: UserService,
    private router: Router,
    private route: ActivatedRoute) {
    this.userForm = this.createFormGroup();
  }

  ngOnInit(): void {
    this.userId = this.route.snapshot.params.id;
  }

  ngAfterViewInit(): void {
    if (this.userId) {
      this.getUser(this.userId);
    }
  }

  getUser(id) {
    this.UserService.getUser(id).pipe(first()).subscribe(
      user => {
        this.loadUser(user);
      }
    )
  }

  loadUser(user) {
    this.mode = "edit";
    console.log(user);
    this.userForm.controls.nombre.setValue(user.nombre);
    this.userForm.controls.apellido.setValue(user.apellido);
    this.userForm.controls.dni.setValue(user.dni);
    this.userForm.controls.fnacimiento.setValue(user.fnacimiento.replace(' ', 'T'));
    this.userForm.controls.cargo.setValue(user.cargo);
    this.userForm.controls.legajo.setValue(user.legajo);
    this.userForm.controls.nombre_usuario.setValue(user.nombre_usuario);
    this.userForm.controls.contrasenia.setValue(user.contrasenia);
    this.userForm.controls.ciudad.setValue(user.ciudad);
    this.userForm.controls.pais.setValue(user.pais);
    this.userForm.controls.tarea.setValue(user.provincia);
    this.userForm.controls.codigo_postal.setValue(user.codigo_postal);
    this.userForm.controls.direccion.setValue(user.direccion);
    this.userForm.controls.correo_electronico.setValue(user.correo_electronico);
  }



  resetForm() {
    this.userForm.reset();
  }

  saveForm() {
    if (this.mode === 'add') {
      this.UserService.postUser(this.userForm).subscribe(
        user => alert("Se ha creado el usuario numero: " + user.id)
      );
    } else {
      this.UserService.updateUser(this.userId, this.userForm).subscribe(
        user => {
          console.log(user);
        });
    }
    this.router.navigate(['main/usuarios']);
  }

  createFormGroup() {
    return new FormGroup({
      nombre: new FormControl(''),
      apellido: new FormControl(''),
      dni: new FormControl(''),
      fnacimiento: new FormControl(''),
      cargo: new FormControl(''),
      legajo: new FormControl(''),
      nombre_usuario: new FormControl(''),
      contrasenia: new FormControl(''),
      ciudad: new FormControl(''),
      pais: new FormControl(''),
      provincia: new FormControl(''),
      codigo_postal: new FormControl(''),
      direccion: new FormControl(''),
      correo_electronico: new FormControl(''),
      estado: new FormControl(30)
    })
  }

}
