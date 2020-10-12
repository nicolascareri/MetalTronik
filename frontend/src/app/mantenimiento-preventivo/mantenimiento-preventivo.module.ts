import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreModule } from '../core/core.module';
import { TablaPlanificacionComponent } from './components/planificacion/tabla-planificacion/tabla-planificacion.component';
import { MantenimientoPreventivoRoutingModule } from "../mantenimiento-preventivo/mantenimiento-preventivo-routing.module";
import { MatTabsModule } from '@angular/material/tabs';

@NgModule({
  declarations: [TablaPlanificacionComponent],
  imports: [
    CommonModule,
    MantenimientoPreventivoRoutingModule,
    MatTabsModule,
    CoreModule
    
  ]
})
export class MantenimientoPreventivoModule { }
