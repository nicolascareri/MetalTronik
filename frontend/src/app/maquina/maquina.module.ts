import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CoreModule } from '../core/core.module';
import { MatTableModule } from '@angular/material/table';
import { MatSliderModule } from '@angular/material/slider';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { TablaComponent } from './components/tabla/tabla.component';
import { FormComponent } from "./components/form/form.component";
import {MatTabsModule} from '@angular/material/tabs';
import { TablaPlantaComponent } from "../planta/components/tabla-planta/tabla-planta.component";
import { TablaSectorComponent } from "../sector/components/tabla-sector/tabla-sector.component";



@NgModule({
  declarations: [TablaComponent,FormComponent, TablaPlantaComponent, TablaSectorComponent],
  imports: [
    CommonModule, 
    RouterModule,
    MatTableModule,
    MatSliderModule,
    ReactiveFormsModule,
    MatInputModule,
    CoreModule,
    MatTabsModule,
    
  ]
})
export class MaquinaModule { }
