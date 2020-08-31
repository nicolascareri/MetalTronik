import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FormComponent} from './components/form/form.component';
import {TablaOrdenesComponent} from './components/tabla/tabla.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: TablaOrdenesComponent
      },
      {
        path: 'form',
        component: FormComponent
      },
      {
        path: 'form/:id',
        component: FormComponent
      }

    ]
    }
]
@NgModule ({
     imports:[

         RouterModule.forChild(routes)

     ],
     exports:[

        RouterModule

     ]

})

export class OrdenesTrabajoRoutingModule{

}
