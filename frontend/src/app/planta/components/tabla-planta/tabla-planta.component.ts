import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {PlantaService} from '../../services/planta.service';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-tabla-planta',
  templateUrl: './tabla-planta.component.html',
  styleUrls: ['./tabla-planta.component.scss']
})
export class TablaPlantaComponent implements OnInit {

  columnsToDisplay: any = ['nombre'];

  dataSourcePlants = new MatTableDataSource();

  form: FormGroup;

  createFormGroup(){
    return new FormGroup({
      nombre: new FormControl('')
    })
  }


  constructor(private PlantaService: PlantaService) {
    this.form = this.createFormGroup();
  }

  applyFilter(filterValue: String) {
    this.dataSourcePlants.filter = filterValue.trim().toLowerCase();
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
    this.dataSourcePlants.filterPredicate = (data, filter: string)  => {
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

    this.PlantaService.getPlantas().subscribe(

      (data: any)  => { // Success
        this.dataSourcePlants.data = data;
        console.log(this.dataSourcePlants);
      },
      (error) => {
        console.error(error);
      }

    );

  }

}
