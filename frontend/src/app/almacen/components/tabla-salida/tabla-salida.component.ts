import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SalidaService } from "../../services/salida.service";

@Component({
  selector: 'app-tabla-salida',
  templateUrl: './tabla-salida.component.html',
  styleUrls: ['./tabla-salida.component.scss']
})
export class TablaSalidaComponent implements OnInit {

  public dataSourceSalida;
  public columnsToDisplay: any[] = [
    {
      id: 1,
      property:'codigoProducto',
      name: 'Codigo de producto',
      sort: 'up',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 2,
      property:'modelo',
      name: 'Modelo',
      sort: '',
      filterValue: '',
      width: '20%'
    },
    {
      id: 3,
      property:'marca',
      name: 'Marca',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 4,
      property:'fecha',
      name: 'Fecha de salida',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 5,
      property:'solicitante',
      name: 'Solicitante',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 6,
      property:'sector',
      name: 'Sector',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
  ];

  constructor(private SalidaSerive: SalidaService,
              private Router: Router) { }

  ngOnInit(): void {
    this.getSalidas();
  }

  getSalidas(){
    this.SalidaSerive.getSalidas().subscribe(
      (data: any)  => {
        this.dataSourceSalida = data;
      },
      (error) => {
        console.error(error);
      }
    );
  }

  clickedRow(row){
    this.Router.navigate(['main/almacen/formsalida/' + row.id]);
  }

}
