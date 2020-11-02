import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { RegistroService } from "../../../services/registro.service";
import { MessageService } from "../../../../core/service/message.service";

@Component({
  selector: 'app-form-registro',
  templateUrl: './form-registro.component.html',
  styleUrls: ['./form-registro.component.scss']
})
export class FormRegistroComponent implements OnInit {


  public options = [{
    "id" : true,
    "descripcion": "Si"
    },
    {
      "id" : false,
      "descripcion": "No"
    }
  ]

  public tarea : any;
  public tareaId : any;
  public tareaForm: FormGroup = new FormGroup({
    fechaPlanificada: new FormControl(''),
    fechaRealizada: new FormControl(''),
    observaciones: new FormControl(''),
    realizo: new FormControl(''),
    tarea_cod: new FormControl('')
  });
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "La tarea se ha programado correctamente";


  constructor( private route: ActivatedRoute,
               private RegistroService: RegistroService,
               private MessageService: MessageService) { }

  ngOnInit(): void {
    this.tareaId = this.route.snapshot.params.id;
  }

  ngAfterViewInit(): void {
    if (this.tareaId) {
      this.getTarea(this.tareaId);
    }
  }

  getTarea(id) {
    this.RegistroService.getRegistroById(id).pipe(first()).subscribe(
      tarea => {
        console.log(tarea);
        this.tarea = tarea;
        this.loadTarea(tarea);
      }
    )
  }

  loadTarea(tarea) {
    
    
    this.tareaForm.controls.fechaPlanificada.setValue(tarea.fechaPlanificada.replace(' ', 'T'));
    if(this.tareaForm.controls.fechaRealizada == null){
      this.tareaForm.controls.fechaRealizada.setValue(tarea.fechaRealizada.replace(' ','T'));
    }
    this.tareaForm.controls.observaciones.setValue(tarea.observaciones);
    this.tareaForm.controls.realizo.setValue(tarea.realizo);
    this.tareaForm.controls.tarea_cod.setValue(tarea.tarea.id);
  }

  
  saveForm() {
      this.RegistroService.updateRegistro(this.tareaId, this.tareaForm).subscribe(
        tarea => {
          this.messageBody = "El registro se ha editado correctamente"
          this.showSuccess();
        },
        error => this.showError(error.error)
        );
    }
  
    
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

  

}
