import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreModule } from '../core/core.module';
import { MatTableModule } from '@angular/material/table';
import { MatSliderModule } from '@angular/material/slider';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { TablaComponent } from './components/tabla/tabla.component';
import { UsuariosRoutingModule } from "./usuarios-routing.module";
import { FormComponent } from "./components/form/form.component";




@NgModule({
  declarations: [TablaComponent,FormComponent],
  imports: [
    CommonModule,
    CoreModule,
    MatSliderModule,
    MatTableModule,
    MatInputModule,
    ReactiveFormsModule,
    RouterModule,
    UsuariosRoutingModule
  ]
})
export class UsuariosModule { }
