import { Component, OnInit } from '@angular/core';
import { FormGroup,  FormControl } from "@angular/forms";
import { PlantaService } from "../../services/planta.service";

@Component({
  selector: 'app-tabla-planta',
  templateUrl: './tabla-planta.component.html',
  styleUrls: ['./tabla-planta.component.scss']
})
export class TablaPlantaComponent implements OnInit {

  columnsToDisplay: any = ['nombre'];

  dataSourcePlants: any;

  form: FormGroup;

  createFormGroup(){
    return new FormGroup({
      nombre: new FormControl('')
    })
  }


  constructor(private PlantaService: PlantaService) 
  {
    this.form = this.createFormGroup();
   }

  ngOnInit(): void {

    this.PlantaService.getPlantas().subscribe(

      (data: any)  => { // Success
        this.dataSourcePlants = data;
        console.log(this.dataSourcePlants);
      },
      (error) => {
        console.error(error);
      }

    );

  }

}
