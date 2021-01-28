import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { LoginComponent } from "./usuarios/auth/login/login.component";


const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  // { path: 'main', loadChildren: './main/main.module#MainModule' }
  // {
  //   path: '',
  //   loadChildren: './main/main.module#MainModule',

  // },

  // {path: '', pathMatch: 'full', redirectTo: 'main'},
  // { path: '**', pathMatch: 'full', redirectTo: 'main' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
