import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import { MatTableDataSource } from "@angular/material/table";
import {SectorService} from '../../services/sector.service';
import { fileURLToPath } from 'url';

@Component({
  selector: 'app-tabla-sector',
  templateUrl: './tabla-sector.component.html',
  styleUrls: ['./tabla-sector.component.scss']
})
export class TablaSectorComponent implements OnInit {


  public columnsToDisplay: any[] = [
    {
      id: 1,
      property:'descripcion',
      name: 'Nombre del sector',
      sort: 'up',
      filterValue: '',
      width: '15%'
    }
  ];

  public dataSourceSectors;

  form: FormGroup;

  createFormGroup(){
    return new FormGroup({
      descripcion: new FormControl('')
    })
  }


  constructor(private SectorService: SectorService) {
    this.form = this.createFormGroup();
  }

  applyFilter(filterValue: String) {
    this.dataSourceSectors.filter = filterValue.trim().toLowerCase();
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
    this.dataSourceSectors.filterPredicate = (data, filter: string)  => {
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

    this.SectorService.getSectores().subscribe(
      (data: any) => {
        this.dataSourceSectors = data;
        console.log(this.dataSourceSectors)
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
