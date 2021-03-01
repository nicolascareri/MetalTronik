import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { RegistroService } from "../../../services/registro.service";
import { MessageService } from "../../../../core/service/message.service";
import { UserService } from "../../../../usuarios/services/user.service";

@Component({
  selector: 'app-form-registro',
  templateUrl: './form-registro.component.html',
  styleUrls: ['./form-registro.component.scss']
})
export class FormRegistroComponent implements OnInit {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public options = [{
    "id" : true,
    "descripcion": "Si"
    },
    {
      "id" : false,
      "descripcion": "No"
    }
  ]
  public users: any;
  public tarea : any;
  public tareaId : any;
  public tareaForm: FormGroup = new FormGroup({
    fechaPlanificada: new FormControl(''),
    fechaRealizada: new FormControl(''),
    observaciones: new FormControl(''),
    realizo: new FormControl(''),
    tarea_cod: new FormControl(''),
    encargado: new FormControl('')
  });
  public routeButton = "../";
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "La tarea se ha programado correctamente";

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  constructor( private route: ActivatedRoute,
               private router: Router,
               private RegistroService: RegistroService,
               private MessageService: MessageService,
               private UserService: UserService) { }

  ngOnInit(): void {
    this.tareaId = this.route.snapshot.params.id;
    this.getUsers();
  }

  ngAfterViewInit(): void {
    if (this.tareaId) {
      this.getTarea(this.tareaId);
    }
  }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  showSuccess(){
    this.MessageService.showSuccess({
      title: this.messageTitleSuccess,
      body: this.messageBody
    });
  }

  showError(message){
    this.MessageService.showError({
      title: this.messageTitleError,
      body: message.errors ? message.errors[0].defaultMessage + ". campo: " + message.errors[0].field + ", Valor rechazado: " + message.errors[0].rejectedValue : message.error
    })
  }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  loadTarea(tarea) {
    this.routeButton = "../../";
    this.tareaForm.controls.fechaPlanificada.setValue(tarea.fechaPlanificada.replace(' ', 'T'));
    if(this.tareaForm.controls.fechaRealizada == null){
      this.tareaForm.controls.fechaRealizada.setValue(tarea.fechaRealizada.replace(' ','T'));
    }
    this.tareaForm.controls.observaciones.setValue(tarea.observaciones);
    this.tareaForm.controls.realizo.setValue(tarea.realizo);
    this.tareaForm.controls.tarea_cod.setValue(tarea.tarea.id);
    this.tareaForm.controls.encargado.setValue(tarea.encargado.id);
  }


  saveForm() {
    this.RegistroService.updateRegistro(this.tareaId, this.tareaForm).subscribe(
      tarea => {
        this.messageBody = "El registro se ha editado correctamente"
        this.showSuccess();
        this.router.navigate(['main/mantenimientosPreventivos/registro']);
      },
      error => this.showError(error.error)
      );
  }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  getTarea(id) {
    this.RegistroService.getRegistroById(id).pipe(first()).subscribe(
      tarea => {
        this.tarea = tarea;
        this.loadTarea(tarea);
      }
    )
  }

  getUsers(){
    this.UserService.getUsers().subscribe(
      (data: any) => {
        this.users = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.nombre + " " + val.apellido
            }
          }
        );
      },
      error => {
        console.log(error.error);
        
      }
    );
  }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  

}
