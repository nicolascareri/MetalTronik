import { Component, OnInit } from '@angular/core';
import { TareaService } from "../../../services/tarea.service";
import { Router } from '@angular/router';
import { CoreService } from 'src/app/core/service/core.service';
import { MessageService } from "../../../../core/service/message.service";
import { RegistroService } from '../../../services/registro.service';

@Component({
  selector: 'app-tabla-planificacion',
  templateUrl: './tabla-planificacion.component.html',
  styleUrls: ['./tabla-planificacion.component.scss']
})

export class TablaPlanificacionComponent implements OnInit {

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public dataSourceTareas;
  public columnsToDisplay: any[] = [
    {
      id: 1,
      property:'sector',
      name: 'Sector',
      sort: 'up',
      filterValue: '',
      width: '15%'
    },
    {
      id: 2,
      property: 'maquina',
      name: 'Codigo de maquina',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 3,
      property: 'parte',
      name: 'Codigo de parte',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 4,
      property: 'tarea',
      name: 'Tarea',
      sort: '',
      filterValue: '',
      width: '35%'
    },
    {
      id: 5,
      property: 'frecuencia',
      name: 'Frecuencia',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 6,
      property: 'inicio',
      name: 'Fecha de inicio',
      sort: '',
      filterValue: '',
      width: '15%'
    } 
  ]

  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "";

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  constructor(private TareaService: TareaService,
              private Router: Router,
              private CoreService: CoreService,
              private RegistroService: RegistroService,
              private MessageService: MessageService) { }

  ngOnInit(): void {
    this.getTareas();
  }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  clickedRow(row){
    this.Router.navigate(['main/mantenimientosPreventivos/formTarea/' + row.id]);
  }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  getTareas(){
    this.TareaService.getTareas().subscribe(
      (data: any) => {
        this.dataSourceTareas = this.CoreService.replaceFormat(data, ['maquina', 'inicio', 'parte']);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  saveCurrent(){
    this.RegistroService.saveCurrent("").subscribe(
      (data: any) => {
        this.messageBody = "Planificacion actual guardada correctamente"
          this.showSuccess();
      },
      error => this.showError(error.error)
    );
  }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
