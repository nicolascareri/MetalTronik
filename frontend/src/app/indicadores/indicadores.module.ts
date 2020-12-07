import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { IndicadoresComponent } from "../indicadores/components/bar-chart/indicadores.component";
import { IndicadoresRoutingModule } from "../indicadores/indicadores-routing.module";
import { ChartsModule } from 'ng2-charts';
import { MainComponent } from './components/main/main/main.component';
import {MatTabsModule} from '@angular/material/tabs';
import { PieChartComponent } from './components/pie-chart/pie-chart.component';



@NgModule({
  declarations: [IndicadoresComponent, MainComponent, PieChartComponent],
  imports: [
    CommonModule,
    IndicadoresRoutingModule,
    ChartsModule,
    MatTabsModule  
  ]
})
export class IndicadoresModule { }
