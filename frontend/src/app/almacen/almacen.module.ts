import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TablaExistenciaComponent } from './components/tabla-existencia/tabla-existencia.component';
import { AlmacenRoutingModule } from "./almacen-rounting.module";
import {MatTabsModule} from '@angular/material/tabs';



@NgModule({
  declarations: [TablaExistenciaComponent],
  imports: [
    CommonModule,
    AlmacenRoutingModule,
    MatTabsModule
  ]
})
export class AlmacenModule { }
