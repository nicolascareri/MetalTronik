import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {FormSectorComponent} from './components/form/form.component';
import {TablaSectorComponent} from './components/tabla-sector/tabla-sector.component';
import {CoreModule} from '../core/core.module';
import {ReactiveFormsModule} from '@angular/forms';
import {MatTableModule} from '@angular/material/table';
import {RouterModule} from '@angular/router';
import { MatInputModule } from '@angular/material/input';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';


@NgModule({
  declarations: [TablaSectorComponent, FormSectorComponent],
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
    TablaSectorComponent, FormSectorComponent
  ]
})
export class SectorModule {
}
