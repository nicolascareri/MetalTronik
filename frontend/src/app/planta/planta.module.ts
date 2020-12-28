import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormPlantaComponent} from './components/form/form.component';
import {TablaPlantaComponent} from './components/tabla-planta/tabla-planta.component';
import {CoreModule} from '../core/core.module';
import {ReactiveFormsModule} from '@angular/forms';
import {MatTableModule} from '@angular/material/table';
import {RouterModule} from '@angular/router';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [TablaPlantaComponent, FormPlantaComponent],
  imports: [
    CommonModule,
    CoreModule,
    ReactiveFormsModule,
    MatTableModule,
    MatInputModule,
    RouterModule,
    MatExpansionModule,
    MatListModule,
    MatIconModule
  ],
  exports: [
    TablaPlantaComponent, FormPlantaComponent
  ]
})
export class PlantaModule {
}
