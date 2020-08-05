import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreModule } from "../core/core.module";
import { TablaRepuestosComponent } from './components/tabla-repuestos/tabla-repuestos.component';
import { RepuestoRoutingModule } from "./repuesto-routing.module";
import {MatCardModule} from '@angular/material/card';
import {MatSelectModule} from '@angular/material/select';
import {MatTableModule} from '@angular/material/table';
import {MatListModule} from '@angular/material/list';
import { ListaRepuestosComponent } from './components/lista-repuestos/lista-repuestos.component';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';

@NgModule({
  declarations: [TablaRepuestosComponent, ListaRepuestosComponent],
  imports: [
    CommonModule,
    RepuestoRoutingModule,
    MatCardModule,
    MatSelectModule,
    MatTableModule,
    MatListModule,
    CoreModule,
    ReactiveFormsModule,
    FormsModule
    
  ]
})
export class RepuestoMaquinaModule { }
