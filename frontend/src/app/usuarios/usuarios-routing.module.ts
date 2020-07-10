import { NgModule, Component } from "@angular/core";
import { RouterModule, Routes } from '@angular/router';
import { TablaComponent } from 'src/app/usuarios/components/tabla/tabla.component';
import { FormComponent } from 'src/app/usuarios/components/form/form.component';

const routes: Routes = [
    {
        path: '',
        children: [ 
            {
                path: '',
                component: TablaComponent
            },
            {
                path: 'tablausuarios', 
                component: TablaComponent
            },
        
            {
                path: 'formuser', 
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