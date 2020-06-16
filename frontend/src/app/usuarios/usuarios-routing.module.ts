import { NgModule, Component } from "@angular/core";
import { RouterModule, Routes } from '@angular/router';
import { TablaComponent } from './components/tabla/tabla.component';
import { FormComponent } from './components/form/form.component';

const routes: Routes = [
    {
        path: '',
        children: [ 
            {
                path: 'table',
                component: TablaComponent
            },
            {
                path: 'user', 
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

export class UsuariosRoutingModule{

}