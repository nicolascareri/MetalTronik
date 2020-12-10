import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SalidaService } from "../../services/salida.service";
import { CoreService } from 'src/app/core/service/core.service';

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
      property:'cantidad',
      name: 'Cantidad',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 2,
      property:'codigoProducto',
      name: 'Codigo de producto',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 3,
      property:'modelo',
      name: 'Modelo',
      sort: '',
      filterValue: '',
      width: '20%'
    },
    {
      id: 4,
      property:'marca',
      name: 'Marca',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 5,
      property:'fecha',
      name: 'Fecha de salida',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 6,
      property:'solicitante',
      name: 'Solicitante',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 7,
      property:'sector',
      name: 'Sector',
      sort: '',
      filterValue: '',
      width: '15%'
    }
  ];

  constructor(private SalidaSerive: SalidaService,
              private Router: Router,
              private coreService: CoreService) { }

  ngOnInit(): void {
    this.getSalidas();
  }

  getSalidas(){
    this.SalidaSerive.getSalidas().subscribe(
      (data: any)  => {
        this.dataSourceSalida = this.coreService.replaceFormat(data, ['solicitante', 'sector', 'codigoProducto', 'fecha', 'modelo', 'marca']);
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
