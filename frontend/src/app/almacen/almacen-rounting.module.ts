import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TablaExistenciaComponent} from "./components/tabla-existencia/tabla-existencia.component";
import {FormSectorComponent} from '../sector/components/form/form.component';
import {FormPlantaComponent} from '../planta/components/form/form.component';


const routes: Routes = [
  {
    path: '',
    children: [
      {
        path: '',
        component: TablaExistenciaComponent
      },
      {
        path: 'formsector',
        component: FormSectorComponent
      },
      {
        path: 'formplanta',
        component: FormPlantaComponent
      },
      // {
      //   path: 'form/:id',
      //   component: FormMaquinaComponent
      // },
      {
        path: 'formsector/:id',
        component: FormSectorComponent
      },
      {
        path: 'formplanta/:id',
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


export class AlmacenRoutingModule{

}
