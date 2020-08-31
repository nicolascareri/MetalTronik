import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { CoreModule } from '../core/core.module';
import { TablaConfiguracionComponent } from './components/tabla/tabla.component';
import { MatTabsModule } from '@angular/material/tabs';
import { ConfiguracionRountingModule } from "./configuracion-routing.module";
import { MatExpansionModule } from '@angular/material/expansion';
import { ListaPrioridadesComponent } from "../prioridad/components/lista-prioridades/lista-prioridades.component";
import { ListaTiposComponent } from "../tipo/components/lista-tipos/lista-tipos.component";
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [TablaConfiguracionComponent, ListaPrioridadesComponent, ListaTiposComponent],
  imports: [
    CommonModule,
    MatTabsModule,
    RouterModule,
    CoreModule,
    ConfiguracionRountingModule,
    MatExpansionModule,
    MatListModule,
    MatIconModule,
    ReactiveFormsModule


  ]
})
export class ConfiguracionModule {
}
