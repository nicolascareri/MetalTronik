import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TablaExistenciaComponent} from "./components/tabla-existencia/tabla-existencia.component";
import {FormExistenciaComponent} from "../almacen/components/form-existencia/form-existencia.component";
import { FormEntradaComponent } from "../almacen/components/form-entrada/form-entrada.component";
import { FormSalidaComponent } from "../almacen/components/form-salida/form-salida.component";
const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: TablaExistenciaComponent
      },
      {
        path: 'formexistencia',
        component: FormExistenciaComponent
      },
      {
        path: 'formexistencia/:id',
        component: FormExistenciaComponent
      },
      {
        path: 'formentrada',
        component: FormEntradaComponent
      },
      {
        path: 'formentrada/:id',
        component: FormEntradaComponent
      },
      {
        path: 'formsalida',
        component: FormSalidaComponent
      },
      {
        path: 'formsalida/:id',
        component: FormSalidaComponent
      },

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


export class AlmacenRoutingModule{

}
