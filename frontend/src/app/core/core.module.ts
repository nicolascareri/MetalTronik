import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import {ButtonComponent} from './components/button/button.component';
import {RouterModule} from '@angular/router';
import { TablaComponent } from './components/tabla/tabla/tabla.component';
import { MatAutocompleteModule } from "@angular/material/autocomplete";


@NgModule({
  declarations: [ButtonComponent, TablaComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    RouterModule,
    MatTableModule,
    MatAutocompleteModule
  ],
  exports: [
    ButtonComponent,
    MatTableModule,
    TablaComponent
    
  ]
})
export class CoreModule { }
