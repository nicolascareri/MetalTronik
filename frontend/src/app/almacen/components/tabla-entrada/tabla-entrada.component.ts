import { Component, OnInit } from '@angular/core';
import { EntradaService } from "../../services/entrada.service";
import { Router } from '@angular/router';
import { CoreService } from 'src/app/core/service/core.service';

@Component({
  selector: 'app-tabla-entrada',
  templateUrl: './tabla-entrada.component.html',
  styleUrls: ['./tabla-entrada.component.scss']
})
export class TablaEntradaComponent implements OnInit {

  public dataSourceEntrada;
  public columnsToDisplay: any[] = [
    {
      id: 1,
      property:'cantidad',
      name: 'Cantidad',
      sort: 'up',
      filterValue: '',
      width: '15%'
    },
    {
      id: 2,
      property: 'fecha',
      name: 'Fecha de entrada',
      sort: '',
      filterValue: '',
      width: '25&'
    }, 
    {
      id: 3,
      property:'numeroOrdenCompra',
      name: 'Nro orden de compra',
      sort: '',
      filterValue: '',
      width: '20%'
    },
    {
      id: 4,
      property:'proveedor',
      name: 'Proveedor',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 5,
      property:'codigoProducto',
      name: 'Cod producto',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 6,
      property:'modelo',
      name: 'Modelo',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 7,
      property:'marca',
      name: 'Marca',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 8,
      property:'ubicacion',
      name: 'Ubicacion',
      sort: '',
      filterValue: '',
      width: '350px'
    },
    
    //Fecha de entrada de la entrada
  ];


  constructor(private EntradaService: EntradaService,
              private Router: Router,
              private coreService: CoreService) { }

  ngOnInit(): void {
    this.getEntradas();
  }

  getEntradas(){
    this.EntradaService.getEntradas().subscribe(
      (data: any)  => {
        this.dataSourceEntrada = this.coreService.replaceFormat(data, ['nombre', 'codigoProducto', 'marca', 'modelo',
                                                                       'tipoRepuesto', 'ubicacion', 'unidad', 
                                                                       'puntoPedido', 'stockObjetivo', 'existencia'
                                                                      ]);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  clickedRow(row){
    this.Router.navigate(['main/almacen/formentrada/' + row.id]);
  }

}
