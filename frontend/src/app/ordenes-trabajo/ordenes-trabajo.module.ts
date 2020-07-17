import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatSliderModule} from '@angular/material/slider';
import {MatTableModule} from '@angular/material/table';
import {RouterModule} from '@angular/router';
import {CoreModule} from '../core/core.module';
import {FormComponent} from './components/form/form.component';
import {TablaComponent} from './components/tabla/tabla.component';
import {OrdenesTrabajoRoutingModule} from './ordenes-trabajo-routing.module';


@NgModule({
  declarations: [FormComponent, TablaComponent],
  imports: [
    RouterModule,
    CommonModule,
    CoreModule,
    OrdenesTrabajoRoutingModule,
    MatSliderModule,
    MatTableModule,
    MatInputModule,
    ReactiveFormsModule,

  ]
})


export class OrdenesTrabajoModule { }
