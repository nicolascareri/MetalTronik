import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreModule } from '../core/core.module';
import { FormComponent } from "./components/form/form.component";
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';



@NgModule({
  declarations: [FormComponent],
  imports: [
    CommonModule,
    CoreModule,
    ReactiveFormsModule,
    RouterModule
  ]
})
export class MantenimientoCorrectivoModule { }
