import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { TablaRepuestosComponent } from "./components/tabla-repuestos/tabla-repuestos.component";
import { ListaRepuestosComponent } from "./components/lista-repuestos/lista-repuestos.component";


const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: TablaRepuestosComponent
      },
      {
        path: 'lista-repuestos',
        component: ListaRepuestosComponent
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
