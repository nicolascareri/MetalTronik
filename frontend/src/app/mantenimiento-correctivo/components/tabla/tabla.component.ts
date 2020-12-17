import { Component, OnInit } from '@angular/core';
import { MantenimientoCorrectivoService } from '../../services/mantenimiento-correctivo.service';
import { CoreService } from 'src/app/core/service/core.service';
import { first } from 'rxjs/operators'
import { Router } from '@angular/router';

@Component({
  selector: 'app-tabla-mantenimiento-correctivo',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaMantenimientoCorrectivoComponent implements OnInit {

  public columnsToDisplay: any[] = [
    {
      id: 1,
      property: 'nrocorrectivo',
      name: 'Nro. correctivo',
      sort: 'up',
      filterValue: '',
      width: '15%'
    },
    {
      id: 2,
      property: 'ordentrabajo_cod',
      name: 'Codigo de orden de trabajo',
      sort: '',
      filterValue: '',
      width: '25%'
    },
    {
      id: 3,
      property: 'maquina',
      name: 'Codigo de maquina',
      sort: '',
      filterValue: '',
      width: '25%'
    },
    {
      id: 4,
      property: 'parte',
      name: 'Codigo de parte',
      sort: '',
      filterValue: '',
      width: '25%'
    },
    {
      id: 5,
      property: 'tipo',
      name: 'Tipo de orden de trabajo',
      sort: '',
      filterValue: '',
      width: '20%'
    },
    {
      id: 6,
      property: 'sector',
      name: 'Sector',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 7,
      property: 'tipo',
      name: 'Tipo de falla',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 8,
      property: 'fechainicio',
      name: 'Fecha de inicio',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 9,
      property: 'fechaFin',
      name: 'Fecha de fin',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 10,
      property: 'tiempoReparacion',
      name: 'Tiempo de reparacion',
      sort: '',
      filterValue: '',
      width: '350px'
    },
    {
      id: 11,
      property: 'horasProduccionAfectadas',
      name: 'Horas de produccion afectadas',
      sort: '',
      filterValue: '',
      width: '350px'
    },
    {
      id: 12,
      property: 'observaciones',
      name: 'Observaciones',
      sort: '',
      filterValue: '',
      width: '20%'
    },
    {
      id: 13,
      property: 'encargo1',
      name: 'Encargado 1',
      sort: '',
      filterValue: '',
      width: '20%'
    },
    {
      id: 14,
      property: 'encargo2',
      name: 'Encargado 2',
      sort: '',
      filterValue: '',
      width: '20%'
    },
    {
      id: 15,
      property: 'encargo3',
      name: 'Encargado 3',
      sort: '',
      filterValue: '',
      width: '20%'
    }
  ];

  public dataSourceMantenimientosCorrectivos;


  constructor(private MantenimientoCorrectivoService: MantenimientoCorrectivoService,
              private coreService: CoreService,
              private router: Router) {

  }

  ngOnInit(): void {
    this.getMantenimientos();
  }

  getMantenimientos(){
    this.MantenimientoCorrectivoService.getMantenimientosCorrectivos().pipe(first()).subscribe(
      (data: any) => {
        this.dataSourceMantenimientosCorrectivos = this.coreService.replaceFormat(data, ['encargo1', 'encargo2', 'encargo3',
          'ordentrabajo', 'maquina', 'parteOrden', 'fechainicio', 'fechaFin', 'tipo']);
      },
      (error) => {
        console.error(error);
      }

    );
  }

  clickedRow(row){
    this.router.navigate(['main/mantenimientosCorrectivos/formcorrectivo/' + row.id]);
  }

}
