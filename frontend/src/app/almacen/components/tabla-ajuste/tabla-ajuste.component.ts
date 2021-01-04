import { Component, OnInit } from '@angular/core';
import { AlmacenService } from "../../services/almacen.service";
import { CoreService } from 'src/app/core/service/core.service';

@Component({
  selector: 'app-tabla-ajuste',
  templateUrl: './tabla-ajuste.component.html',
  styleUrls: ['./tabla-ajuste.component.scss']
})
export class TablaAjusteComponent implements OnInit {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public dataSourceAjuste;
  public columnsToDisplay: any[] = [
    {
      id: 1,
      property:'codigoProducto',
      name: 'Código de producto',
      sort: 'up',
      filterValue: '',
      width: '25%'
    },
    {
      id: 2,
      property:'modelo',
      name: 'Modelo',
      sort: 'up',
      filterValue: '',
      width: '25%'
    },
    {
      id: 3,
      property:'nombre',
      name: 'Nombre',
      sort: 'up',
      filterValue: '',
      width: '25%'
    },
    {
      id: 4,
      property:'fecha_correccion',
      name: 'Fecha de corrección',
      sort: 'up',
      filterValue: '',
      width: '25%'
    },
  ];

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  constructor(private AlmacenService: AlmacenService,
              private coreService: CoreService) { }

  ngOnInit(): void {
    this.getAjustes();
  }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  getAjustes(){
    this.AlmacenService.getAjustes().subscribe(
      (data: any) => {
        this.dataSourceAjuste =  this.coreService.replaceFormat(data, ['nombre', 'codigoProducto', 'marca', 'modelo']);
      },
      (error) => {
        console.log(error.error);
      }
    );
  }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
