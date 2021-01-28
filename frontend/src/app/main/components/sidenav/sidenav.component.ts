import { Component } from '@angular/core';
import { AuthService } from "../../../usuarios/services/auth.service";



@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})


export class SidenavComponent{

  constructor(private AuthService: AuthService) {}

  public mobileQuery: MediaQueryList;
  public selected: any;

  fillerNav = [
    { name: 'Ordenes', route: 'ordenes', icon: '' },
    { name: 'Mantenimiento correctivo', route: 'mantenimientosCorrectivos', icon: '' },
    { name: 'Mantenimiento preventivo', route: 'mantenimientosPreventivos', icon: '' },
    { name: 'Máquinas', route: 'maquinas', icon: '' },
    { name: 'Almacén', route: 'almacen', icon: '' },
    { name: 'Asociación de repuestos', route: 'repuestos', icon: '' },
    { name: 'Personal', route: 'personal', icon: '' },
    { name: 'Indicadores', route: 'indicadores', icon: '' },
    { name: '', route: 'configuracion', icon: 'settings'}
  ]
  
  logout(){
    this.AuthService.logout();
  }
  

  clicked(object) {
      this.selected = object;
  }


}
