import { Component } from '@angular/core';



@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})


export class SidenavComponent{

  public mobileQuery: MediaQueryList;
  public selected: any;

  fillerNav = [
    { name: 'Ordenes', route: 'ordenes', icon: 'calendar_today' },
    { name: 'Mantenimiento Correctivo', route: 'mantenimientosCorrectivos', icon: 'assignment' },
    { name: 'Máquinas', route: 'maquinas', icon: 'build' },
    { name: 'Almacén', route: 'almacen', icon: 'settings' },
    { name: 'Asociación de repuestos', route: 'repuestos', icon: 'build_circle' },
    { name: 'Usuarios', route: 'usuarios', icon: 'recent_actors' },
    { name: 'Indicadores', route: '', icon: 'assessment' },
    { name: 'Configuración', route: 'configuracion', icon: 'settings' }
  ]

  

  clicked(object) {
      this.selected = object;
  }


}
