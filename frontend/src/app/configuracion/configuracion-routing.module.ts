import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TablaConfiguracionComponent } from "./components/tabla/tabla.component";

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: TablaConfiguracionComponent
      }

    ]
  }
];

@NgModule({
  imports: [

    RouterModule.forChild(routes)

  ],
  exports: [

    RouterModule

  ]

})

export class ConfiguracionRountingModule {

}
