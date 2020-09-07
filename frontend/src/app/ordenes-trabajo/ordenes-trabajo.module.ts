import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatSliderModule} from '@angular/material/slider';
import {MatTableModule} from '@angular/material/table';
import {RouterModule} from '@angular/router';
import {CoreModule} from '../core/core.module';
import {FormComponent} from './components/form/form.component';
import {TablaOrdenesComponent} from './components/tabla/tabla.component';
import {OrdenesTrabajoRoutingModule} from './ordenes-trabajo-routing.module';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';


@NgModule({
  declarations: [FormComponent, TablaOrdenesComponent],
  imports: [
    RouterModule,
    CommonModule,
    CoreModule,
    OrdenesTrabajoRoutingModule,
    MatSliderModule,
    MatTableModule,
    MatInputModule,
    ReactiveFormsModule,
    MatIconModule,
    MatCardModule

  ]
})


export class OrdenesTrabajoModule { }
