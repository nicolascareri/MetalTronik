import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FormMantenimientoCorrectivoComponent} from '../matenimiento-correctivo/components/form/form.component';
import {TablaMantenimientoCorrectivoComponent} from './components/tabla/tabla.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: TablaMantenimientoCorrectivoComponent
      },
      {
        path: 'formcorrectivo',
        component: FormMantenimientoCorrectivoComponent
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
