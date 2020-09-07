import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FormUsuarioComponent} from 'src/app/usuarios/components/form/form.component';
import {TablaUsuarioComponent} from 'src/app/usuarios/components/tabla/tabla.component';

const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: TablaUsuarioComponent
      },
      {
        path: 'form',
        component: FormUsuarioComponent
      },
      {
        path: 'form/:id',
        component: FormUsuarioComponent
      }
    ]
  }
]
@NgModule ({
     imports: [

         RouterModule.forChild(routes)

     ],
     exports:[

        RouterModule

     ]

})

export class UsuariosRoutingModule{

}
