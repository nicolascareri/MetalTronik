import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TablaPlanificacionComponent } from "../mantenimiento-preventivo/components/planificacion/tabla-planificacion/tabla-planificacion.component";
import { FormPlanificacionComponent } from "./components/planificacion/form-planificacion/form-planificacion.component";

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: TablaPlanificacionComponent
      },
      {
        path: 'formTarea',
        component: FormPlanificacionComponent
      },
      {
        path: 'formTarea/:id',
        component: FormPlanificacionComponent
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
