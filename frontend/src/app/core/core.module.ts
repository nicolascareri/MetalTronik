import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { ButtonComponent } from './components/button/button.component';
import { RouterModule } from '@angular/router';
import { OrdenesTrabajoRoutingModule } from '../ordenes-trabajo/ordenes-trabajo-routing.module';
import { UsuariosRoutingModule } from "../usuarios/usuarios-routing.module";
import { MaquinaRoutingModule } from "../maquina/maquina-routing.module";



@NgModule({
  declarations: [ButtonComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    RouterModule,
    OrdenesTrabajoRoutingModule,
    UsuariosRoutingModule,
    MaquinaRoutingModule
  ],
  exports: [
    ButtonComponent
  ]
})
export class CoreModule { }
