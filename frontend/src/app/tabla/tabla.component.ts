import { Component, OnInit } from '@angular/core';
import { UserService } from "../user.service";
import {MatTableDataSource} from '@angular/material/table';
import {OrdenestrabajoService} from '../ordenestrabajo.service';

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})



export class TablaComponent implements OnInit {

  //users: any = [];
  //columnsToDisplay: any = ['id', 'nombre', 'apellido', 'dni'];
  ordenes: any = [];
  columnsToDisplay: any = ['ordentrabajo_cod','planta', 'maquina.sector', 'maquina.maquina_cod', 
                           'pedidoMateriales', 'tarea.descripcion', 'priodidad', 'tipo', 'fechaEntrega', 'fechaRealizar',
                           'encargo.nombre', 'responsable.nombre', 'estado'];
  dataSource: any ;
  constructor( private OrdenestrabajoService: OrdenestrabajoService) {}

  
  

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    console.log(this.dataSource.filter);
    
  }


  ngOnInit(): void {

    

    this.OrdenestrabajoService.getAllOrdenes().subscribe(

      (data: any)  => { // Success
        this.ordenes = data;
        this.dataSource = new MatTableDataSource(this.ordenes);
      },
      (error) => {
        console.error(error);
      }

    );

    
  }

}
