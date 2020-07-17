import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatSliderModule} from '@angular/material/slider';
import {MatTableModule} from '@angular/material/table';
import {RouterModule} from '@angular/router';
import {CoreModule} from '../core/core.module';
import {FormUsuarioComponent} from './components/form/form.component';
import {TablaUsuarioComponent} from './components/tabla/tabla.component';
import {UsuariosRoutingModule} from './usuarios-routing.module';


@NgModule({
  declarations: [TablaUsuarioComponent, FormUsuarioComponent],
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
