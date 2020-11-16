import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {ReactiveFormsModule} from '@angular/forms';
import {MatInputModule} from '@angular/material/input';
import {MatTableModule} from '@angular/material/table';
import {MatTabsModule} from '@angular/material/tabs';
import {RouterModule} from '@angular/router';
import {CoreModule} from '../core/core.module';
import {FormMaquinaComponent} from './components/form/form.component';
import {TablaMaquinaComponent} from './components/tabla/tabla.component';
import {PlantaModule} from '../planta/planta.module';
import {SectorModule} from '../sector/sector.module';
import {MaquinaRoutingModule} from './maquina-routing.module';
import { FormPartesComponent } from './components/form/form-partes/form-partes.component';
import { FormPartesIIComponent } from './components/form/form-partes/form-partes-ii/form-partes-ii.component';


@NgModule({
  declarations: [TablaMaquinaComponent, FormMaquinaComponent, FormPartesComponent, FormPartesIIComponent],
  imports: [
    CommonModule,
    RouterModule,
    MatTableModule,
    ReactiveFormsModule,
    MatInputModule,
    CoreModule,
    MatTabsModule,
    PlantaModule,
    SectorModule,
    MaquinaRoutingModule,

  ]
})

export class MaquinaModule {}
