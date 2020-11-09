import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { SidenavComponent } from 'src/app/main/components/sidenav/sidenav.component';
import { HomeComponent } from './components/home/home.component';
import { MainComponent } from './components/main/main.component';
import { MainRoutingModule } from './main-routing.module';
import { FooterComponent } from './components/footer/footer.component';
import { ChartsModule } from 'ng2-charts';

import { IndicadoresComponent } from "../indicadores/indicadores/indicadores.component";


@NgModule({
  declarations: [MainComponent, HomeComponent, SidenavComponent, FooterComponent, IndicadoresComponent],
  imports: [
    CommonModule,
    MainRoutingModule,
    MatListModule,
    MatSidenavModule,
    MatIconModule,
    MatToolbarModule,
    ChartsModule
    
  ]
})
export class MainModule { }
