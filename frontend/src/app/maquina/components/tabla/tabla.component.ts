import {Component, OnInit} from '@angular/core';
import {MaquinaService} from '../../services/maquina.service';
import { CoreService } from 'src/app/core/service/core.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-tabla-maquina',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaMaquinaComponent implements OnInit {

  public columnsToDisplay: any[] = [
    {
      id: 1,
      property:'planta',
      name: 'Planta',
      sort: 'up',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 2,
      property:'sector',
      name: 'Sector',
      sort: '',
      filterValue: '',
      width: '14%'
    },
    {
      id: 3,
      property:'maquina_cod',
      name: 'Codigo de maquina',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 4,
      property:'nro_serie',
      name: 'Nro de serie',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 5,
      property:'modelo',
      name: 'Modelo',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 6,
      property:'equipo',
      name: 'Equipo',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 7,
      property:'datos_tecnicos',
      name: 'Datos tecnicos',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 8,
      property:'descripcion',
      name: 'Descripcion',
      sort: '',
      filterValue: '',
      width: '350px'
    }
  ];

  public dataSourceMachines;


  constructor(private MaquinaService: MaquinaService,
              private coreService: CoreService,
              private router: Router) {
   }


  ngOnInit(): void {
    this.getMaquinas();
  }

  clickedRow(row){
    this.router.navigate(['main/maquinas/form/' + row.id]);
  }

  getMaquinas(){
    this.MaquinaService.getMaquinas().subscribe(
      (data: any)  => {
        this.dataSourceMachines = this.coreService.replaceFormat(data, ['maquina', 'encargo1', 'encargo2', 'encargo3',
        'responsable', 'prioridad', 'tipo', 'fechaEntrega', 'fechaRealizar', 'ordentrabajo_cod', 'estado', 'tipo', 'planta', 'sector']);
      },
      (error) => {
        console.error(error);
      }
    );
  }

}
