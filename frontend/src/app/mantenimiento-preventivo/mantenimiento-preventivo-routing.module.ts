import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { TablaPlanificacionComponent } from "../mantenimiento-preventivo/components/planificacion/tabla-planificacion/tabla-planificacion.component";

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: TablaPlanificacionComponent
      }
    ]
  }
]



@NgModule({
  imports:[

         RouterModule.forChild(routes)

     ],
     exports:[

        RouterModule

     ]

})
export class MantenimientoPreventivoRoutingModule { }
