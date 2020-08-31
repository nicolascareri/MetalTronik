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

  public columnsToDisplay: any[] = [
    {
      id: 1,
      property:'nombre',
      name: 'Nombre de la planta',
      sort: 'up',
      filterValue: '',
      width: '15%'
    }
  ];


  public dataSourcePlants;

  constructor(private PlantaService: PlantaService) {
  }


  ngOnInit(): void {

    this.PlantaService.getPlantas().subscribe(

      (data: any)  => { // Success
        this.dataSourcePlants = data;
      },
      (error) => {
        console.error(error);
      }

    );

  }

}
