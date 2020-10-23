import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { RegistroService } from '../../../services/registro.service';
import { CoreService } from 'src/app/core/service/core.service';
import * as moment from 'moment';

@Component({
  selector: 'app-tabla-registro',
  templateUrl: './tabla-registro.component.html',
  styleUrls: ['./tabla-registro.component.scss']
})
export class TablaRegistroComponent implements OnInit {

  public dataSourceTareas;
  public seachForm: FormGroup = new FormGroup({
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

  ]
  

  constructor(private RegistroService: RegistroService,
              private CoreService : CoreService) { }

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

    let date = this.seachForm.value;
    date = this.getFormatDate(date.fecha);
    console.log(date);
    
    this.RegistroService.getRegistro(date).subscribe(
      (data: any)  => {
        
        console.log(data);
        
        this.dataSourceTareas = this.CoreService.replaceFormat(data, ['fechaPlanificada', 'tarea']);

      },
      (error) => {
        console.log(error);
        
      }
    );
    
  }

}
