import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TablaPlanificacionComponent } from "../mantenimiento-preventivo/components/planificacion/tabla-planificacion/tabla-planificacion.component";
import { TablaRegistroComponent } from "../mantenimiento-preventivo/components/registro/tabla-registro/tabla-registro.component";
import { FormPlanificacionComponent } from "./components/planificacion/form-planificacion/form-planificacion.component";
import { FormRegistroComponent } from "../mantenimiento-preventivo/components/registro/form-registro/form-registro.component";

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: TablaPlanificacionComponent
      },
      {
        path: 'planificacion',
        component: TablaPlanificacionComponent
      },
      {
        path: 'registro',
        component: TablaRegistroComponent
      },
      {
        path: 'planificacion/formTarea',
        component: FormPlanificacionComponent
      },
      {
        path: 'formTarea',
        component: FormPlanificacionComponent
      },
      {
        path: 'formTarea/:id',
        component: FormPlanificacionComponent
      },
      {
        path: 'formRegistro',
        component: FormRegistroComponent
      },
      {
        path: 'formRegistro/:id',
        component: FormRegistroComponent
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
