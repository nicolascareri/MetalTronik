import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreModule } from '../core/core.module';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MatTableModule } from '@angular/material/table';
import { MatSliderModule } from '@angular/material/slider';
import { MatInputModule } from '@angular/material/input';

import { FormComponent } from "./components/form/form.component";
import { TablaComponent } from "./components/tabla/tabla.component";



@NgModule({
  declarations: [FormComponent, TablaComponent],
  imports: [
    CommonModule,
    CoreModule,
    ReactiveFormsModule,
    RouterModule,
    MatTableModule,
    MatSliderModule,
    MatInputModule
  ]
})
export class MantenimientoCorrectivoModule { }
