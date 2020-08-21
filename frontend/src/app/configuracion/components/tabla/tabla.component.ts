import { Component, OnInit } from '@angular/core';



@Component({
  selector: 'app-tabla-configuracion',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaConfiguracionComponent implements OnInit {

  panelOpenState1 = false;
  panelOpenState2 = false;

  constructor() {
  }

  ngOnInit(): void {
  }

}
