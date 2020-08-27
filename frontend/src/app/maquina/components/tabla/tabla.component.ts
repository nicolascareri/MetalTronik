import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {FormControl, FormGroup} from '@angular/forms';
import {MaquinaService} from '../../services/maquina.service';


@Component({
  selector: 'app-tabla-maquina',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaMaquinaComponent implements OnInit {

  public columnsToDisplay: any[] = [
    {
      id: 1,
      property:'planta',
      name: 'Planta',
      sort: 'up',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 2,
      property:'sector',
      name: 'Sector',
      sort: '',
      filterValue: '',
      width: '14%'
    },
    {
      id: 3,
      property:'maquina_cod',
      name: 'Codigo de maquina',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 4,
      property:'nro_serie',
      name: 'Nro de serie',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 5,
      property:'modelo',
      name: 'Modelo',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 6,
      property:'equipo',
      name: 'Equipo',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 7,
      property:'datos_tecnicos',
      name: 'Datos tecnicos',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 8,
      property:'descripcion',
      name: 'Descripcion',
      sort: '',
      filterValue: '',
      width: '350px'
    }
  ];

  public dataSourceMachines;

  form: FormGroup;
  // Editar
  @Input() originalUser: any;

  createFormGroup() {
    return new FormGroup({
      id: new FormControl(''),
      maquina_cod: new FormControl('')
    })
  }
  @Output() close = new EventEmitter();

  constructor(private MaquinaService: MaquinaService) {
    this.form = this.createFormGroup();
   }

  applyFilter(filterValue: String) {
    this.dataSourceMachines.filter = filterValue.trim().toLowerCase();
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
    this.dataSourceMachines.filterPredicate = (data, filter: string)  => {
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
    

    this.MaquinaService.getMaquinas().subscribe(

      (data: any)  => { // Success
        this.dataSourceMachines = data;
        console.log(this.dataSourceMachines);
      },
      (error) => {
        console.error(error);
      }

    );

  }

}
