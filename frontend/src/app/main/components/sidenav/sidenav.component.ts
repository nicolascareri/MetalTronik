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
    { name: 'Ordenes', route: 'ordenes', icon: '' },
    { name: 'Mantenimiento correctivo', route: 'mantenimientosCorrectivos', icon: '' },
    { name: 'Mantenimiento preventivo', route: 'mantenimientosPreventivos', icon: '' },
    { name: 'Máquinas', route: 'maquinas', icon: '' },
    { name: 'Almacén', route: 'almacen', icon: '' },
    { name: 'Asociación de repuestos', route: 'repuestos', icon: '' },
    { name: 'Usuarios', route: 'usuarios', icon: '' },
    { name: 'Indicadores', route: 'indicadores', icon: '' },
    { name: '', route: 'configuracion', icon: 'settings'}
  ]

  

  clicked(object) {
      this.selected = object;
  }


}
