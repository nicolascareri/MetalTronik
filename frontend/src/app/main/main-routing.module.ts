import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { MainComponent } from './components/main/main.component';


const routes: Routes = [
  {
    path: 'main',
    component: MainComponent,
    children: [
      {
        path: '',
        redirectTo: 'home',
        pathMatch: 'prefix'
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
      },
      {
        path: 'mantenimientosCorrectivos',
        loadChildren: 'src/app/mantenimiento-correctivo/mantenimiento-correctivo.module#MantenimientoCorrectivoModule',
      },
      {
        path: 'configuracion',
        loadChildren: 'src/app/configuracion/configuracion.module#ConfiguracionModule',
      },
      {
        path: 'repuestos',
        loadChildren: 'src/app/repuesto-maquina/repuesto-maquina.module#RepuestoMaquinaModule',
      },
      {
        path: 'almacen',
        loadChildren: 'src/app/almacen/almacen.module#AlmacenModule',
      },
      {
        path: 'mantenimientosPreventivos',
        loadChildren: 'src/app/mantenimiento-preventivo/mantenimiento-preventivo.module#MantenimientoPreventivoModule'
      },
      {
        path: 'indicadores',
        loadChildren: 'src/app/indicadores/indicadores.module#IndicadoresModule'
      }


    ]

  },
  { path: '', pathMatch: 'full', redirectTo: 'main' }
];
@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [

    RouterModule

  ]
})

export class MainRoutingModule {

}
