import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormComponent } from './form/form.component';
import { OrdenestrabajoComponent } from "./ordenestrabajo/ordenestrabajo.component";


const routes: Routes = [
  { path: 'form', component: FormComponent},
  { path: 'ordenes', component: OrdenestrabajoComponent},
  { path: '**', component: OrdenestrabajoComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
