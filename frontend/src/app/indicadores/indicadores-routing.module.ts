import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { MainComponent } from "../indicadores/components/main/main/main.component";



const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: MainComponent
      }
    ]
    }
];
@NgModule ({
     imports: [

         RouterModule.forChild(routes)

     ],
     exports:[

        RouterModule

     ]

})

export class IndicadoresRoutingModule{

}
