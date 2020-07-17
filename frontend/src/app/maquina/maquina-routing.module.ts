import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FormMaquinaComponent} from 'src/app/maquina/components/form/form.component';
import {TablaMaquinaComponent} from 'src/app/maquina/components/tabla/tabla.component';
import {FormSectorComponent} from '../sector/components/form/form.component';
import {FormPlantaComponent} from '../planta/components/form/form.component';


const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: TablaMaquinaComponent
      },
      {
        path: 'form',
        component: FormMaquinaComponent
      },
      {
        path: 'formsector',
        component: FormSectorComponent
      },
      {
        path: 'formplanta',
        component: FormPlantaComponent
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

export class MaquinaRoutingModule{

}
