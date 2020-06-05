import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreModule } from '../core/core.module';
import { OrdenesTrabajoRoutingModule } from './ordenes-trabajo-routing.module';
import { FormComponent } from './components/form/form.component';
import { TablaComponent } from './components/tabla/tabla.component';
import { MatTableModule } from '@angular/material/table';
import { MatSliderModule } from '@angular/material/slider';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { EditOrderComponent } from './components/edit-order/edit-order.component';




@NgModule({
  declarations: [FormComponent, TablaComponent, EditOrderComponent],
  imports: [
    RouterModule,
    CommonModule,
    CoreModule,
    OrdenesTrabajoRoutingModule,
    MatSliderModule,
    MatTableModule,
    MatInputModule,
    ReactiveFormsModule,
    
  ]
})


export class OrdenesTrabajoModule { }
