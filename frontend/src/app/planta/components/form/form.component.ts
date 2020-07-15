import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { PlantaService } from "../../services/planta.service";

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {

  createFormGroup(){
    return new FormGroup({
      nombre: new FormControl(''),
      estado: new FormControl(30)
    })
  }

  formPlanta: FormGroup;

  constructor(private PlantaService: PlantaService) 
  {
    this.formPlanta  = this.createFormGroup();
   }

  ngOnInit(): void {
  }

  resetForm() {
    this.formPlanta.reset();
  }
  
  saveForm() {
    console.log(this.formPlanta.value);
    this.PlantaService.postPlanta(this.formPlanta).subscribe(
      planta => alert("Se ha creado la planta numero: " + planta.id)
    );
  }

}
