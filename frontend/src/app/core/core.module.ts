import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatTableModule} from '@angular/material/table';
import {MatButtonModule} from '@angular/material/button';
import {ButtonComponent} from './components/button/button.component';
import {RouterModule} from '@angular/router';
import { TablaComponent } from './components/tabla/tabla/tabla.component';
import { InputComponent } from './components/input/input.component';
import {ReactiveFormsModule} from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import { ToastrModule } from "ngx-toastr";


@NgModule({
  declarations: [ButtonComponent, TablaComponent, InputComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    RouterModule,
    MatTableModule,
    ReactiveFormsModule,
    MatCardModule,
    ToastrModule.forRoot()
  ],
  exports: [
    ButtonComponent,
    MatTableModule,
    TablaComponent,
    InputComponent,
    ToastrModule
    
  ]
})
export class CoreModule { }
