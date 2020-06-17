import { NgModule } from '@angular/core';
import { Routes, RouterModule } from "@angular/router";
import { MainComponent } from './components/main/main.component';
import { HomeComponent } from './components/home/home.component';


const routes: Routes = [
    {
        path: 'main',
        component: MainComponent,
        children: [
            {
                path: '',
                redirectTo: 'home',
                pathMatch: 'full'
            
            },
            {
                path: 'home',
                component: HomeComponent
            },
            {
                path: 'ordenes',
                loadChildren: 'src/app/ordenes-trabajo/ordenes-trabajo.module#OrdenesTrabajoModule',
            },
            {
                path: 'usuarios',
                loadChildren: 'src/app/usuarios/usuarios.module#UsuariosModule',
            },
            {
                path: 'maquinas',
                loadChildren: 'src/app/maquina/maquina.module#MaquinaModule',
            }
            

        ]

    },
    { path: '', pathMatch: 'full', redirectTo: 'main' }
];
@NgModule ({
    imports:[   
        RouterModule.forChild(routes)
    ],
    exports:[

        RouterModule

    ]
})

export class MainRoutingModule{

}