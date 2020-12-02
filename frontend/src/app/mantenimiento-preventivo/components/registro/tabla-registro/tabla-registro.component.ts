import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { RegistroService } from '../../../services/registro.service';
import { CoreService } from 'src/app/core/service/core.service';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { MessageService } from "../../../../core/service/message.service";

@Component({
  selector: 'app-tabla-registro',
  templateUrl: './tabla-registro.component.html',
  styleUrls: ['./tabla-registro.component.scss']
})
export class TablaRegistroComponent implements OnInit {

  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "Resultados encontrados";
  public mesaggeTitleWarning: any = "WARNING";
  public messageBodyWarning: any;
  public dataSourceTareas;
  public dataSourceSaves;
  public formForTask: FormGroup = new FormGroup({
    fecha: new FormControl('')
  });
  public formForSaves :FormGroup = new FormGroup({
    fecha: new FormControl('')
  });

  public columnsToDisplay: any[] = [
    {
      id: 1,
      property:'fechaPlanificada',
      name: 'Fecha planificada',
      sort: 'up',
      filterValue: '',
      width: '35%'
    },
    {
      id: 2,
      property: 'tareaNombre',
      name: 'Tarea',
      sort: '',
      filterValue: '',
      width: '65%'
    },
    {
      id: 3,
      property: 'maquina',
      name: 'Maquina',
      sort: '',
      filterValue: '',
      width: '35%'
    },
    {
      id: 4,
      property: 'sector',
      name: 'Sector',
      sort: '',
      filterValue: '',
      width: '35%'
    },

  ];

  public columnsToDisplayForSaves: any[] = [
    {
      id: 1,
      property:'fechaPlanificada',
      name: 'Fecha planificada',
      sort: 'up',
      filterValue: '',
      width: '35%'
    },
    {
      id: 2,
      property: 'tareaNombre',
      name: 'Tarea',
      sort: '',
      filterValue: '',
      width: '65%'
    },
    {
      id: 3,
      property: 'maquina',
      name: 'Maquina',
      sort: '',
      filterValue: '',
      width: '35%'
    },
    {
      id: 4,
      property: 'sector',
      name: 'Sector',
      sort: '',
      filterValue: '',
      width: '35%'
    },
    {
      id: 5,
      property: 'realizo',
      name: 'Realizada',
      sort: '',
      filterValue: '',
      width: '35%'
    },
    {
      id:6,
      property: 'encargado',
      name: 'Encargado',
      sort: '',
      filtervalue: '',
      width: '55%'
    },
    {
      id: 7,
      property: 'fechaRealizada',
      name: 'Fecha de realización',
      sort: '',
      filterValue: '',
      width: '35%'
    },
    {
      id: 8,
      property: 'observaciones',
      name: 'Observaciones',
      sort: '',
      filterValue: '',
      width: '35%'
    },

  ];

 
  

  constructor(private RegistroService: RegistroService,
              private CoreService : CoreService,
              private Router: Router,
              private MessageService: MessageService) { }

  ngOnInit(): void {  
  
  }

 
  getFormatDate(date: string): string {
    var dateServerFormat = 'YYYY-MM-DD';
    moment.locale('es');
    const dateMoment = moment(date, dateServerFormat);
    const year = dateMoment.format('YYYY');
    const month = dateMoment.format('MM');
    const day = dateMoment.format('DD');
    return year + "-" + month[0].toUpperCase() + month.substr(1) + "-" + day;
  }

  getTareas(){

    let date = this.formForTask.value;
    date = this.getFormatDate(date.fecha);
    this.RegistroService.getRegistro(date).subscribe(
      (data: any)  => {
        
        console.log(data);
        
        if(data.length != 0){
          this.showSuccess();
          this.dataSourceTareas = this.CoreService.replaceFormat(data, ['fechaPlanificada', 'tarea']);

        }else{
          this.mesaggeTitleWarning = "Tareas no encontradas en la fecha indicada"
          this.showWarning();
        }

      },
      error => this.showError(error.error)
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

  getSaves(){
    let date = this.formForSaves.value;
    date = this.getFormatDate(date.fecha);
    this.RegistroService.getSaves(date).subscribe(
      (data: any) => {
        this.messageBody = "Registros encontrados"
        this.showSuccess();
        this.dataSourceSaves = this.CoreService.replaceFormat(data, ['fechaPlanificada', 'tarea', 'fechaRealizada', 'realizo', 'encargado']);


      },
      error => this.showError(error.error)
    );
  }

  clickedRow(row){
    this.Router.navigate(['main/mantenimientosPreventivos/formRegistro/' + row.id]);
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

  showWarning(){
    this.MessageService.showWarning({
      title: this.mesaggeTitleWarning,
      body: this.messageBodyWarning
    })
  }

}
