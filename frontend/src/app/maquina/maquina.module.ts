import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreModule } from '../core/core.module';
import { MatTableModule } from '@angular/material/table';
import { MatSliderModule } from '@angular/material/slider';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { TablaComponent } from './components/tabla/tabla.component';
// import { FormComponent } from "./components/form/form.component";



@NgModule({
  declarations: [TablaComponent],
  imports: [
    CommonModule, 
    RouterModule,
    MatTableModule,
    MatSliderModule,
    ReactiveFormsModule,
    MatInputModule,
    CoreModule
  ]
})
export class MaquinaModule { }
