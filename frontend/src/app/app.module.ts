import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserService } from './usuarios/services/user.service';
import { HttpClientModule} from '@angular/common/http';
import { UsuarioComponent } from './usuarios/components/usuario/usuario.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { OrdenestrabajoService } from "./ordenes-trabajo/services/ordenestrabajo.service";
import { CoreModule } from './core/core.module';
import { OrdenesTrabajoModule } from "./ordenes-trabajo/ordenes-trabajo.module";
import { MainModule } from './main/main.module';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';






@NgModule({
  declarations: [
    AppComponent,
    UsuarioComponent,
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
    MatListModule
  ],
  providers: [UserService, 
    OrdenestrabajoService,],
  bootstrap: [AppComponent]
})


export class AppModule { }
