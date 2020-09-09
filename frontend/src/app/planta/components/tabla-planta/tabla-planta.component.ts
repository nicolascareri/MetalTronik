import {Component, OnInit} from '@angular/core';
import {PlantaService} from '../../services/planta.service';
import { Router } from '@angular/router';


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
      width: '80%'
    }
  ];


  public dataSourcePlants;

  constructor(private PlantaService: PlantaService,
              private router: Router) {
  }


  ngOnInit(): void {

   this.getPlantas();

  }

  clickedRow(row){
    this.router.navigate(['main/maquinas/formplanta/' + row.id]);
  }

  getPlantas(){
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
