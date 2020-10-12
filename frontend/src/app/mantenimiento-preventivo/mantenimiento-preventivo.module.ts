import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreModule } from '../core/core.module';
import { TablaPlanificacionComponent } from './components/planificacion/tabla-planificacion/tabla-planificacion.component';
import { MantenimientoPreventivoRoutingModule } from "../mantenimiento-preventivo/mantenimiento-preventivo-routing.module";
import { MatTabsModule } from '@angular/material/tabs';
import { ReactiveFormsModule } from '@angular/forms';
import { FormPlanificacionComponent } from './components/planificacion/form-planificacion/form-planificacion.component';

@NgModule({
  declarations: [TablaPlanificacionComponent, FormPlanificacionComponent],
  imports: [
    CommonModule,
    MantenimientoPreventivoRoutingModule,
    MatTabsModule,
    CoreModule,
    ReactiveFormsModule
    
  ]
})
export class MantenimientoPreventivoModule { }
