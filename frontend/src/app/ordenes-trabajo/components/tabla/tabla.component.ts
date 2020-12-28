import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { first } from 'rxjs/operators'
import { ESTADO_ORDEN, ESTADOTABLE } from 'src/app/core/constants/constants';
import { OrdenestrabajoService } from '../../services/ordenestrabajo.service';
import { UserService } from '../../../usuarios/services/user.service';
import { MaquinaService } from '../../../maquina/services/maquina.service';
import { TipoService } from '../../../tipo/services/tipo.service';
import { CoreService } from 'src/app/core/service/core.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-tablaOrdenes',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})


export class TablaOrdenesComponent implements OnInit {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public columnsToDisplay: any[] = [
    {
      id: 1,
      property:'ordentrabajo_id',
      name: 'Nro. Orden de Trabajo',
      sort: 'up',
      filterValue: '',
      width: '15%'
    },
    {
      id: 2,
      property:'estado',
      name: 'Estado',
      sort: '',
      filterValue: '',
      width: '20%'
    },  
    {
      id: 3,
      property:'maquina',
      name: 'Codigo de Maquina',
      sort: '',
      filterValue: '',
      width: '14%'
    },
    {
      id:4,
      property:'parte',
      name:'Codigo de parte',
      sort:'',
      filtervalue: '',
      width:'15%'
    },
    {
      id: 5,
      property:'planta',
      name: 'Planta',
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
    {
      id: 7,
      property:'tarea',
      name: 'Tarea',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 8,
      property:'tipo',
      name: 'Tipo',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 9,
      property:'prioridad',
      name: 'Prioridad',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 10,
      property:'fechaEntrega',
      name: 'Fecha Entrega',
      sort: '',
      filterValue: '',
      width: '350px'
    }, 
    {
      id: 11,
      property:'fechaRealizar',
      name: 'Fecha Realizar',
      sort: '',
      filterValue: '',
      width: '350px'
    },
    {
      id: 12,
      property:'encargo',
      name: 'Encargó',
      sort: '',
      filterValue: '',
      width: '20%'
    }, 
    {
      id: 13,
      property:'responsable',
      name: 'Responsable',
      sort: '',
      filterValue: '',
      width: '20%'
    }, 
    {
      id: 14,
      property:'ordenTerciarizacion',
      name: 'Nro. Orden de Terciarizacion',
      sort: '',
      filterValue: '',
      width: '20%'
    }
  ];
  // public ordenForm: FormGroup;
  // public form: FormGroup;
  public dataSourceOrdenes;
  public dataSourceUsers: any;
  public dataSourceSectors: any;
  public dataSourceMachines: any;
  public dataSourcePlants: any;
  public dataSourcePrioridades: any;
  public dataSourceTipos: any;
  public DataOrderToEdit: any;
  public estadosTable: any = ESTADOTABLE;
  public estadosForm: any = ESTADO_ORDEN;

  @Input() originalOrder: any;
  @Output() close = new EventEmitter();

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  constructor(private OrdenestrabajoService: OrdenestrabajoService,
    private userService: UserService,
    private maquinaService: MaquinaService,
    private tiposService: TipoService,
    private coreService: CoreService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.getOrdenes();
    this.getTipos();
    this.getMaquinas();
    this.getUsuarios();
  }

  clickedRow(row){
    this.router.navigate(['main/ordenes/form/' + row.ordentrabajo_id]);
  }

  openForm(ordenId){
    this.router.navigate(['main/ordenes/form/'+ ordenId]);
  }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  getOrdenes(){
    this.OrdenestrabajoService.getAllOrdenes()
    .pipe(first())
    .subscribe(
      (data: any) => {
        this.dataSourceOrdenes = this.coreService.replaceFormat(data, ['maquina', 'parte', 'encargo', 'responsable', 'prioridad', 'tipo', 'fechaEntrega', 'fechaRealizar', 'ordentrabajo', 'estado']);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  // getPrioridades(){
  //   this.prioridadesService.getPrioridades().subscribe(
  //     (data: any) => {
  //       this.dataSourcePrioridades = data;
  //     },
  //     (error) => {
  //       console.error(error);
  //     }
  //   );
  // }

  getTipos(){
    this.tiposService.getTipos('Ordenes').subscribe(
      (data: any) => {
        this.dataSourceTipos = data;
      },
      (error) => {
        console.error(error);
      }
    );
  }

  // getPlantas(){
  //   this.plantaService.getPlantas().subscribe(
  //     (data: any) => { // Success
  //       this.dataSourcePlants = data;
  //     },
  //     (error) => {
  //       console.error(error);
  //     }
  //   );
  // }

  getMaquinas(){
    this.maquinaService.getMaquinas().subscribe(
      (data: any) => { // Success
        this.dataSourceMachines = data;
      },
      (error) => {
        console.error(error);
      }
    );
  }

  // getSectores(){
  //   this.sectorService.getSectores().subscribe(
  //     (data: any) => { // Success
  //       this.dataSourceSectors = data;
  //     },
  //     (error) => {
  //       console.error(error);
  //     }
  //   );
  // }

  getUsuarios(){
    this.userService.getUsers().subscribe(
      (data: any) => { // Success
        this.dataSourceUsers = data;

      },
      (error) => {
        console.error(error);
      }
    );
  }

  // createFormGroup() {
  //   return new FormGroup({
  //     encargo_cod: new FormControl(''),
  //     estado: new FormControl(''),
  //     fechaRealizar: new FormControl(''),
  //     maquina_cod: new FormControl(''),
  //     pedidoMateriales: new FormControl(''),
  //     priodidad_cod: new FormControl(''),
  //     responsable_cod: new FormControl(''),
  //     tarea: new FormControl(''),
  //     observaciones: new FormControl(''),
  //     ordenTerciarizacion: new FormControl(''),
  //     fechaEntrega: new FormControl(''),
  //     tipo_cod: new FormControl(''),

  //   })
  // }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  

}
