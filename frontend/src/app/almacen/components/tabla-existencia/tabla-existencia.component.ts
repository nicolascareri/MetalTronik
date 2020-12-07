import { Component, OnInit } from '@angular/core';
import { AlmacenService } from "../../services/almacen.service";
import { Router } from '@angular/router';


@Component({
  selector: 'app-tabla-existencia',
  templateUrl: './tabla-existencia.component.html',
  styleUrls: ['./tabla-existencia.component.scss']
})
export class TablaExistenciaComponent implements OnInit {

  public dataSourceRepuestos;

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
      name: 'Stock mínimo',
      sort: '',
      filtervalue: '',
      width: '25%'
    },
    {
      id: 10,
      property: 'stockObjetivo',
      name: 'Stock objetivo',
      sort: '',
      filtervalue: '',
      width: '25%'
    },
    {
      id: 11,
      property: 'existencia',
      name: 'Stock actual',
      sort: '',
      filtervalue: '',
      width: '25%'
    }
      
  ];


  constructor(private AlmacenService: AlmacenService,
              private Router: Router
              ) { }

  ngOnInit(): void {
    this.getRepuestos();
  }

  getRepuestos(){
    this.AlmacenService.getRepuestos().subscribe(
      (data: any)  => {
        this.dataSourceRepuestos = data;
      },
      (error) => {
        console.error(error);
      }
    );
  }

  clickedRow(row){
    this.Router.navigate(['main/almacen/formexistencia/' + row.id]);
  }

}
