import { Component, OnInit } from '@angular/core';
import { MaquinaService } from "../../../maquina/services/maquina.service";

@Component({
  selector: 'app-tabla-repuestos',
  templateUrl: './tabla-repuestos.component.html',
  styleUrls: ['./tabla-repuestos.component.scss']
})
export class TablaRepuestosComponent implements OnInit {

  seleccion = '-';

  idMaquinaSeleccion = this.seleccion; 

  dataSourceMaquinas: any;

  constructor(private MaquinaService: MaquinaService) { }

  ngOnInit(): void {
    

    this.MaquinaService.getMaquinas().subscribe(
      (data: any) => {
        this.dataSourceMaquinas = data;
        console.log(this.dataSourceMaquinas)
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
