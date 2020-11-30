import { Component, OnInit } from '@angular/core';
import { TareaService } from "../../../services/tarea.service";
import { Router } from '@angular/router';
import { CoreService } from 'src/app/core/service/core.service';

@Component({
  selector: 'app-tabla-planificacion',
  templateUrl: './tabla-planificacion.component.html',
  styleUrls: ['./tabla-planificacion.component.scss']
})

export class TablaPlanificacionComponent implements OnInit {

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
      width: '15%'
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

  constructor(private TareaService: TareaService,
              private Router: Router,
              private CoreService: CoreService) { }

  ngOnInit(): void {
    this.getTareas();
  }

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

  clickedRow(row){
    this.Router.navigate(['main/mantenimientosPreventivos/formTarea/' + row.id]);
  }

}
