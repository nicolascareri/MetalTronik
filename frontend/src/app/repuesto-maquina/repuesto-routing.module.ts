import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { TablaRepuestosComponent } from "./components/tabla-repuestos/tabla-repuestos.component";


const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: TablaRepuestosComponent
      }

    ]
    }
];
@NgModule ({
     imports: [

         RouterModule.forChild(routes)

     ],
     exports:[

        RouterModule

     ]

})

export class RepuestoRoutingModule{

}
