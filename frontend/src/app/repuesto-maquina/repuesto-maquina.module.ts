import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TablaRepuestosComponent } from './components/tabla-repuestos/tabla-repuestos.component';
import { RepuestoRoutingModule } from "./repuesto-routing.module";
import {MatCardModule} from '@angular/material/card';
import {MatSelectModule} from '@angular/material/select';
import {MatTableModule} from '@angular/material/table';



@NgModule({
  declarations: [TablaRepuestosComponent],
  imports: [
    CommonModule,
    RepuestoRoutingModule,
    MatCardModule,
    MatSelectModule,
    MatTableModule
    
  ]
})
export class RepuestoMaquinaModule { }
