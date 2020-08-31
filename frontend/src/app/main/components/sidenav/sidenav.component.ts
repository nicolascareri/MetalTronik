import {ChangeDetectorRef, Component, OnDestroy} from '@angular/core';
import {MediaMatcher} from '@angular/cdk/layout';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})


export class SidenavComponent implements OnDestroy {



  mobileQuery: MediaQueryList;

  fillerNav = [
    {name: 'Inicio', route: '', icon: 'home'},
    {name: 'Ordenes', route: 'ordenes', icon: 'calendar_today'},
    {name: 'Mantenimiento Correctivo', route: 'mantenimientosCorrectivos', icon: 'assignment'},
    {name: 'Maquinas', route: 'maquinas', icon: 'build'},
    {name: 'Repuestos', route: 'repuestos', icon: 'build_circle'},
    {name: 'Usuarios', route: 'usuarios', icon: 'recent_actors'},
    {name: 'Indicadores', route: '', icon: 'assessment'},
    {name: 'Configuracion', route: 'configuracion', icon: 'settings'}
  ]


  private _mobileQueryListener: () => void;

  constructor(changeDetectorRef: ChangeDetectorRef, media: MediaMatcher) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  shouldRun = true;

}
