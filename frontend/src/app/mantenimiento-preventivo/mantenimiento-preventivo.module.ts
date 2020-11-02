import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreModule } from '../core/core.module';
import { TablaPlanificacionComponent } from './components/planificacion/tabla-planificacion/tabla-planificacion.component';
import { MantenimientoPreventivoRoutingModule } from "../mantenimiento-preventivo/mantenimiento-preventivo-routing.module";
import { MatTabsModule } from '@angular/material/tabs';
import { ReactiveFormsModule } from '@angular/forms';
import { FormPlanificacionComponent } from './components/planificacion/form-planificacion/form-planificacion.component';
import { TablaRegistroComponent } from './components/registro/tabla-registro/tabla-registro.component';
import { FormRegistroComponent } from './components/registro/form-registro/form-registro.component';

@NgModule({
  declarations: [TablaPlanificacionComponent, FormPlanificacionComponent, TablaRegistroComponent, FormRegistroComponent],
  imports: [
    CommonModule,
    MantenimientoPreventivoRoutingModule,
    MatTabsModule,
    CoreModule,
    ReactiveFormsModule
    
  ]
})
export class MantenimientoPreventivoModule { }
