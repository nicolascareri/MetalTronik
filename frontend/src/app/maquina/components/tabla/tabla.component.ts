import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { FormGroup, FormControl } from "@angular/forms";
import { MaquinaService } from "../../services/maquina.service";




@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaComponent implements OnInit {

  columnsToDisplay: any = ['id', 'planta.nombre','sector.descripcion', 'maquina_cod','nro_serie','modelo','equipo','datos_tecnicos','descripcion' ];

  dataSourceMachines = new MatTableDataSource();
  form: FormGroup;
  

  createFormGroup(){
    return new FormGroup({
      id: new FormControl(''),
      maquina_cod: new FormControl('')
    })
  }

  //Editar
  @Input() originalUser: any; 
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

  filter(){
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


    this.filter();


  }

}
