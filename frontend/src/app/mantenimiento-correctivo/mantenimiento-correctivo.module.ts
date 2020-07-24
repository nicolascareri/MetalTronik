import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatSliderModule} from '@angular/material/slider';
import {MatTableModule} from '@angular/material/table';
import {RouterModule} from '@angular/router';
import {CoreModule} from '../core/core.module';
import {FormMantenimientoCorrectivoComponent} from './components/form/form.component';
import {TablaMantenimientoCorrectivoComponent} from './components/tabla/tabla.component';
import {MantenimientoCorrectivoRoutingModule} from './mantenimiento-correctivo-routing.module';


@NgModule({
  declarations: [FormMantenimientoCorrectivoComponent, TablaMantenimientoCorrectivoComponent],
  imports: [
    CommonModule,
    CoreModule,
    ReactiveFormsModule,
    RouterModule,
    MatTableModule,
    MatSliderModule,
    MatInputModule,
    MantenimientoCorrectivoRoutingModule
  ]
})
export class MantenimientoCorrectivoModule { }
