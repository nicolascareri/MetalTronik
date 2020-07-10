import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FormComponent } from "../matenimiento-correctivo/components/form/form.component";
import { TablaComponent } from "./components/tabla/tabla.component";

const routes: Routes = [
  {
      path: '',
      children: [ 
          {
              path: 'tablacorrectivos',
              component: TablaComponent
          },
          {
              path: 'formcorrectivo', 
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
