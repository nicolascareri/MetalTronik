import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TablaExistenciaComponent} from "./components/tabla-existencia/tabla-existencia.component";
import { TablaEntradaComponent } from "./components/tabla-entrada/tabla-entrada.component";
import { TablaSalidaComponent } from "./components/tabla-salida/tabla-salida.component";
import { TablaAjusteComponent } from "./components/tabla-ajuste/tabla-ajuste.component";
import {FormExistenciaComponent} from "../almacen/components/form-existencia/form-existencia.component";
import { FormEntradaComponent } from "../almacen/components/form-entrada/form-entrada.component";
import { FormSalidaComponent } from "../almacen/components/form-salida/form-salida.component";
import { FormAjusteComponent } from "../almacen/components/form-ajuste/form-ajuste.component";


const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: TablaExistenciaComponent
      },
      {
        path: 'existencia',
        component: TablaExistenciaComponent
      },
      {
        path: 'entrada',
        component: TablaEntradaComponent
      },
      {
        path: 'salida',
        component: TablaSalidaComponent
      },
      {
        path: 'ajuste',
        component: TablaAjusteComponent
      },
      {
        path: 'existencia/formexistencia',
        component: FormExistenciaComponent
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
        path: 'entrada/formentrada',
        component: FormEntradaComponent
      },
      {
        path: 'formentrada/:id',
        component: FormEntradaComponent
      },
      {
        path: 'salida/formsalida',
        component: FormSalidaComponent
      },
      {
        path: 'formsalida/:id',
        component: FormSalidaComponent
      },
      {
        path: 'ajuste/formajuste',
        component: FormAjusteComponent
      },
      {
        path: 'formajuste/:id',
        component: FormAjusteComponent
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


export class AlmacenRoutingModule{

}
