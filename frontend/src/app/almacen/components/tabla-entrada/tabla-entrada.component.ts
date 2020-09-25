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
      property:'nombre',
      name: 'Nombre',
      sort: 'up',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 2,
      property:'codigoProducto',
      name: 'Codigo de producto',
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
      property:'modelo',
      name: 'Modelo',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 5,
      property:'precio',
      name: 'Precio',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 6,
      property:'tipoRepuesto',
      name: 'Tipo de repuesto',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 7,
      property:'ubicacion',
      name: 'Ubicacion',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 8,
      property:'unidad',
      name: 'Unidad',
      sort: '',
      filterValue: '',
      width: '350px'
    },
    {
      id: 9,
      property: 'puntoPedido',
      name: 'Punto pedido',
      sort: '',
      filtervalue: ' ',
      width: '25%'
    },
    {
      id: 10,
      property: 'stockObjetivo',
      name: 'Stock objetivo',
      sort: '',
      filtervalue: ' ',
      width: '25%'
    },
    {
      id: 11,
      property: 'existencia',
      name: 'Existencia',
      sort: '',
      filtervalue: ' ',
      width: '25%'
    }
      
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
