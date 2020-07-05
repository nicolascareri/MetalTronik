import { NgModule, Component } from "@angular/core";
import { RouterModule, Routes } from '@angular/router';
import { TablaComponent } from 'src/app/maquina/components/tabla/tabla.component';
import { FormComponent } from 'src/app/maquina/components/form/form.component';

const routes: Routes = [
    {
        path: '',
        children: [ 
            {
                path: '',
                component: TablaComponent
            },
            {
                path: 'tabla', 
                component: TablaComponent
            },
            {
                path: 'machine', 
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

export class MaquinaRoutingModule{

}