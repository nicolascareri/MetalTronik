import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TablaSectorComponent } from './components/tabla-sector/tabla-sector.component';
import { FormComponent } from './components/form/form.component';



@NgModule({
  declarations: [TablaSectorComponent, FormComponent],
  imports: [
    CommonModule
  ]
})
export class SectorModule { }
