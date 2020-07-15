import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TablaPlantaComponent } from './components/tabla-planta/tabla-planta.component';
import { FormComponent } from './components/form/form.component';



@NgModule({
  declarations: [TablaPlantaComponent, FormComponent],
  imports: [
    CommonModule
  ]
})
export class PlantaModule { }
