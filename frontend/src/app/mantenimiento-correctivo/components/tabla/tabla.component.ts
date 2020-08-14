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

  dataSourceMantenimientosCorrectivos = new MatTableDataSource();

  form: FormGroup;

  columnsToDisplay: any = ['nrocorrectivo', 'ordenTrabajo.ordentrabajo_cod', 'ordenTrabajo.tipo.nombre',
    'ordenTrabajo.maquina.sector.descripcion', 'tipofalla', 'fechainicio', 'fechaFin', 'tiempoReparacion',
    'horasProduccionAfectadas', 'observaciones', 'encargo1', 'encargo2', 'encargo3'
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

    this.filtro();

    this.MantenimientoCorrectivoService.getMantenimientosCorrectivos().subscribe(

      (data: any)  => { // Success
        this.dataSourceMantenimientosCorrectivos.data = data;
      },
      (error) => {
        console.error(error);
      }

    );



  }

}
