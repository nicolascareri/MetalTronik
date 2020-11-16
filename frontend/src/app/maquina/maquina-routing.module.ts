import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {FormMaquinaComponent} from 'src/app/maquina/components/form/form.component';
import {TablaMaquinaComponent} from 'src/app/maquina/components/tabla/tabla.component';
import {FormSectorComponent} from '../sector/components/form/form.component';
import {FormPlantaComponent} from '../planta/components/form/form.component';
import { FormPartesComponent } from "../maquina/components/form/form-partes/form-partes.component";
import { FormPartesIIComponent } from "../maquina/components/form/form-partes/form-partes-ii/form-partes-ii.component";


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
      },
      {
        path: 'form/:id',
        component: FormMaquinaComponent
      },
      {
        path: 'formsector/:id',
        component: FormSectorComponent
      },
      {
        path: 'formplanta/:id',
        component: FormPlantaComponent
      },
      {
        path: 'form-partes',
        component: FormPartesComponent
      },
      {
        path: 'form-partes-asoc',
        component: FormPartesIIComponent
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
