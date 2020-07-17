import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatButtonModule} from '@angular/material/button';
import {ButtonComponent} from './components/button/button.component';
import {RouterModule} from '@angular/router';


@NgModule({
  declarations: [ButtonComponent],
  imports: [
    CommonModule,
    MatButtonModule,
    RouterModule,
  ],
  exports: [
    ButtonComponent
  ]
})
export class CoreModule { }
