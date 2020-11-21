import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndicadoresComponent } from "../indicadores/components/indicadores/indicadores.component";
import { IndicadoresRoutingModule } from "../indicadores/indicadores-routing.module";
import { ChartsModule } from 'ng2-charts';
import { MainComponent } from './components/main/main/main.component';
import {MatTabsModule} from '@angular/material/tabs';



@NgModule({
  declarations: [IndicadoresComponent, MainComponent],
  imports: [
    CommonModule,
    IndicadoresRoutingModule,
    ChartsModule,
    MatTabsModule  
  ]
})
export class IndicadoresModule { }
