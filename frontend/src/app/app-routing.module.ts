import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';


const routes: Routes = [
  {
    path: '',
    loadChildren: './main/main.module#MainModule',

  },

  {path: '', pathMatch: 'full', redirectTo: 'main'},
  { path: '**', pathMatch: 'full', redirectTo: 'main' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
