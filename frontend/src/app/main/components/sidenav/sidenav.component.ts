import { Component } from '@angular/core';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})


export class SidenavComponent{

  mobileQuery: MediaQueryList;

  fillerNav = [
    { name: 'Inicio', route: '', icon: 'home' },
    { name: 'Ordenes', route: 'ordenes', icon: 'calendar_today' },
    { name: 'Mantenimiento Correctivo', route: 'mantenimientosCorrectivos', icon: 'assignment' },
    { name: 'Maquinas', route: 'maquinas', icon: 'build' },
    { name: 'Repuestos', route: 'repuestos', icon: 'build_circle' },
    { name: 'Usuarios', route: 'usuarios', icon: 'recent_actors' },
    { name: 'Indicadores', route: '', icon: 'assessment' },
    { name: 'Configuracion', route: 'configuracion', icon: 'settings' }
  ]


 
}
