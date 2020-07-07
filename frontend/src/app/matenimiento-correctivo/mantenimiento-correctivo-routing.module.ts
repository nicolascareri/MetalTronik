import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormComponent } from "../matenimiento-correctivo/components/form/form.component";

const routes: Routes = [
  {
      path: '',
      children: [ 
          // {
          //     path: '',
          //     component: TablaComponent
          // },
          {
              path: 'formulario', 
              component: FormComponent
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
export class MantenimientoCorrectivoRoutingModule { }
