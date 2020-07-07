import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserService } from './usuarios/services/user.service';
import { HttpClientModule} from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { OrdenestrabajoService } from "./ordenes-trabajo/services/ordenestrabajo.service";
import { CoreModule } from './core/core.module';
import { OrdenesTrabajoModule } from "./ordenes-trabajo/ordenes-trabajo.module";
import { MainModule } from './main/main.module';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { UsuariosModule } from "./usuarios/usuarios.module";
import { MaquinaModule } from "./maquina/maquina.module";
import { MantenimientoCorrectivoModule } from './matenimiento-correctivo/mantenimiento-correctivo.module';










@NgModule({
  declarations: [
    AppComponent,
    
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    CoreModule,
    OrdenesTrabajoModule,
    MainModule,
    MatSidenavModule,
    MatListModule,
    UsuariosModule,
    MaquinaModule,
    MantenimientoCorrectivoModule
  ],
  providers: [UserService, 
    OrdenestrabajoService,],
  bootstrap: [AppComponent]
})


export class AppModule { }
