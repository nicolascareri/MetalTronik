import {Component, OnInit} from '@angular/core';
import {FormGroup} from '@angular/forms';
import {MatTableDataSource} from '@angular/material/table';
import {MantenimientoCorrectivoService} from '../../services/mantenimiento-correctivo.service';

@Component({
  selector: 'app-tabla-mantenimiento-correctivo',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaMantenimientoCorrectivoComponent implements OnInit {

  dataSourceMantenimientosCorrectivos;

  form: FormGroup;

  

  public columnsToDisplay: any[] = [
    {
      id: 1,
      property:'nrocorrectivo',
      name: 'Nro. correctivo',
      sort: 'up',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 2,
      property:'ordenTrabajo.ordentrabajo_cod',
      name: 'Codigo de orden de trabajo',
      sort: '',
      filterValue: '',
      width: '14%'
    },
    {
      id: 3,
      property:'ordenTrabajo.tipo.nombre',
      name: 'Tipo de orden de trabajo',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 4,
      property:'ordenTrabajo.maquina.sector.descripcion',
      name: 'Sector',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 5,
      property:'tipofalla',
      name: 'Tipo de falla',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 6,
      property:'fechainicio',
      name: 'Fecha de inicio',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 7,
      property:'fechaFin',
      name: 'Fecha de fin',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 8,
      property:'tiempoReparacion',
      name: 'Tiempo de reparacion',
      sort: '',
      filterValue: '',
      width: '350px'
    }, 
    {
      id: 9,
      property:'horasProduccionAfectadas',
      name: 'Horas de produccion afectadas',
      sort: '',
      filterValue: '',
      width: '350px'
    },
    {
      id: 10,
      property:'observaciones',
      name: 'Observaciones',
      sort: '',
      filterValue: '',
      width: '20%'
    }, 
    {
      id: 11,
      property:'encargo1',
      name: 'Encargado 1',
      sort: '',
      filterValue: '',
      width: '20%'
    }, 
    {
      id: 12,
      property:'encargo2',
      name: 'Encargado 2',
      sort: '',
      filterValue: '',
      width: '20%'
    },  
    {
      id: 13,
      property:'encargo3',
      name: 'Encargado 3',
      sort: '',
      filterValue: '',
      width: '20%'
    }
  ];


  constructor(private MantenimientoCorrectivoService: MantenimientoCorrectivoService) {
  }

  applyFilter(filterValue: String) {
    this.dataSourceMantenimientosCorrectivos.filter = filterValue.trim().toLowerCase();
  }

  nestedFilterCheck(search, data, key) {
    if (typeof data[key] === 'object') {
      for (const k in data[key]) {
        if (data[key][k] !== null) {
          search = this.nestedFilterCheck(search, data[key], k);
        }
      }
    } else {
      search += data[key];
    }
    return search;
  }

  filtro(){
    this.dataSourceMantenimientosCorrectivos.filterPredicate = (data, filter: string)  => {
      const accumulator = (currentTerm, key) => {
        return this.nestedFilterCheck(currentTerm, data, key);
      };
      const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
      // Transform the filter by converting it to lowercase and removing whitespace.
      const transformedFilter = filter.trim().toLowerCase();
      return dataStr.indexOf(transformedFilter) !== -1;
    };
  }

  ngOnInit(): void {


    this.MantenimientoCorrectivoService.getMantenimientosCorrectivos().subscribe(

      (data: any)  => { // Success
        this.dataSourceMantenimientosCorrectivos = data;
      },
      (error) => {
        console.error(error);
      }

    );



  }

}
