import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { MatSliderModule } from "@angular/material/slider";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserService } from './user.service';
import { HttpClientModule} from '@angular/common/http';
import { UsuarioComponent } from './usuario/usuario.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TablaComponent } from './tabla/tabla.component';
import { MatTableModule } from "@angular/material/table";
import {MatInputModule} from '@angular/material/input';
import {OrdenestrabajoService} from "./ordenestrabajo.service";
import { ButtonComponent } from './button/button.component';
import {MatButtonModule} from '@angular/material/button';
import { FromComponent } from './from/from.component';
import { FormComponent } from './form/form.component';
import { FormsModule }   from '@angular/forms';
import {MatSelectModule} from '@angular/material/select';





@NgModule({
  declarations: [
    AppComponent,
    UsuarioComponent,
    TablaComponent,
    ButtonComponent,
    FromComponent,
    FormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatTableModule,
    MatInputModule,
    MatButtonModule,
    FormsModule,
    MatSelectModule
  ],
  providers: [UserService, 
    OrdenestrabajoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
