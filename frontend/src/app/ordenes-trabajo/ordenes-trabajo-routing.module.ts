import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FormComponent} from './components/form/form.component';
import {TablaComponent} from './components/tabla/tabla.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: TablaComponent
      },
      {
        path: 'form',
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
