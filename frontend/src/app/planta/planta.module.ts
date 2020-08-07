import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormPlantaComponent} from './components/form/form.component';
import {TablaPlantaComponent} from './components/tabla-planta/tabla-planta.component';
import {CoreModule} from '../core/core.module';
import {ReactiveFormsModule} from '@angular/forms';
import {MatTableModule} from '@angular/material/table';
import {RouterModule} from '@angular/router';
import {TablaSectorComponent} from '../sector/components/tabla-sector/tabla-sector.component';
import { MatInputModule } from '@angular/material/input';


@NgModule({
  declarations: [TablaPlantaComponent, FormPlantaComponent],
  imports: [
    CommonModule,
    CoreModule,
    ReactiveFormsModule,
    MatTableModule,
    MatInputModule,
    RouterModule
  ],
  exports: [
    TablaPlantaComponent, FormPlantaComponent
  ]
})
export class PlantaModule {
}
