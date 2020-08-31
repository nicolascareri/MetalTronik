import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { first } from 'rxjs/operators'
import { ESTADO_ORDEN, ESTADOTABLE } from 'src/app/core/constants/constants';
import { OrdenestrabajoService } from '../../services/ordenestrabajo.service';
import { PlantaService } from '../../../planta/services/planta.service';
import { SectorService } from '../../../sector/services/sector.service';
import { UserService } from '../../../usuarios/services/user.service';
import { MaquinaService } from '../../../maquina/services/maquina.service';
import { PrioridadesService } from '../../../prioridad/services/prioridades.service';
import { TipoService } from '../../../tipo/services/tipo.service';
import { CoreService } from 'src/app/core/service/core.service';
import { from } from 'rxjs';


@Component({
  selector: 'app-tablaOrdenes',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})


export class TablaOrdenesComponent implements OnInit {


  public columnsToDisplay: any[] = [
    {
      id: 1,
      property:'ordentrabajo_cod',
      name: 'Nro. Orden de Trabajo',
      sort: 'up',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 2,
      property:'maquina',
      name: 'Codigo de Maquina',
      sort: '',
      filterValue: '',
      width: '14%'
    },
    {
      id: 3,
      property:'planta',
      name: 'Planta',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 4,
      property:'sector',
      name: 'Sector',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 5,
      property:'tarea',
      name: 'Tarea',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 6,
      property:'tipo',
      name: 'Tipo',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 7,
      property:'prioridad',
      name: 'Prioridad',
      sort: '',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 8,
      property:'fechaEntrega',
      name: 'Fecha Entrega',
      sort: '',
      filterValue: '',
      width: '350px'
    }, 
    {
      id: 9,
      property:'fechaRealizar',
      name: 'Fecha Realizar',
      sort: '',
      filterValue: '',
      width: '350px'
    },
    {
      id: 10,
      property:'encargo',
      name: 'Encargó',
      sort: '',
      filterValue: '',
      width: '20%'
    }, 
    {
      id: 11,
      property:'responsable',
      name: 'Responsable',
      sort: '',
      filterValue: '',
      width: '20%'
    }, 
    {
      id: 12,
      property:'estado',
      name: 'Estado',
      sort: '',
      filterValue: '',
      width: '20%'
    },  
    {
      id: 13,
      property:'ordenTerciarizacion',
      name: 'Nro. Orden de Terciarizacion',
      sort: '',
      filterValue: '',
      width: '20%'
    }
  ];

  public ordenForm: FormGroup;
  public form: FormGroup;
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

  constructor(private OrdenestrabajoService: OrdenestrabajoService,
    private userService: UserService,
    private sectorService: SectorService,
    private maquinaService: MaquinaService,
    private plantaService: PlantaService,
    private prioridadesService: PrioridadesService,
    private tiposService: TipoService,
    private coreService: CoreService
    ) { }

  ngOnInit(): void {
    this.form = this.createFormGroup();
    this.getOrdenes();
    this.getPrioridades();
    this.getTipos();
    this.getPlantas();
    this.getSectores();
    this.getMaquinas();
    this.getUsuarios();
    this.closeModal();
    this.transformToEdit(this.dataSourceOrdenes);
  }

  clickedRow(row){
    console.log(row);
  }

  getOrdenes(){
    this.OrdenestrabajoService.getAllOrdenes()
    .pipe(first())
    .subscribe(
      (data: any) => {
        this.dataSourceOrdenes = this.coreService.replaceFormat(data, ['maquina', 'encargo', 'responsable', 'prioridad', 'tipo', 'fechaEntrega', 'fechaRealizar', 'ordentrabajo', 'estado']);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  getPrioridades(){
    this.prioridadesService.getPrioridades().subscribe(
      (data: any) => {
        this.dataSourcePrioridades = data;
      },
      (error) => {
        console.error(error);
      }
    );
  }

  getTipos(){
    this.tiposService.getTipos().subscribe(
      (data: any) => {
        this.dataSourceTipos = data;
      },
      (error) => {
        console.error(error);
      }
    );
  }

  getPlantas(){
    this.plantaService.getPlantas().subscribe(
      (data: any) => { // Success
        this.dataSourcePlants = data;
      },
      (error) => {
        console.error(error);
      }
    );
  }

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

  getSectores(){
    this.sectorService.getSectores().subscribe(
      (data: any) => { // Success
        this.dataSourceSectors = data;
      },
      (error) => {
        console.error(error);
      }
    );
  }

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

  createFormGroup() {
    return new FormGroup({
      encargo_cod: new FormControl(''),
      estado: new FormControl(''),
      fechaRealizar: new FormControl(''),
      maquina_cod: new FormControl(''),
      pedidoMateriales: new FormControl(''),
      // planta: new FormControl(''),
      priodidad_cod: new FormControl(''),
      responsable_cod: new FormControl(''),
      // sector: new FormControl(''),
      tarea: new FormControl(''),
      observaciones: new FormControl(''),
      ordenTerciarizacion: new FormControl(''),
      fechaEntrega: new FormControl(''),
      tipo_cod: new FormControl(''),

    })
  }

  openModal(ordenes) {
    let modal = document.getElementById("myModal");
    modal.style.display = "block";
    this.originalOrder = ordenes;
    this.setOriginalValues(this.originalOrder);
  }

  closeModal() {
    window.onclick = function (event) {
      let modal = document.getElementById("myModal");
      if (event.target == modal) {
        modal.style.display = "none";

      }
    }
  }

  setOriginalValues(originalOrder) {
    this.form.setValue({
      encargo_cod: originalOrder.encargo.id,
      estado: originalOrder.estado,
      fechaRealizar: originalOrder.fechaRealizar.toString().replace(" ", "T"),
      maquina_cod: originalOrder.maquina.maquina_cod,
      pedidoMateriales: originalOrder.pedidoMateriales,
      // planta: originalOrder.maquina.planta.id,
      priodidad_cod: originalOrder.priodidad.id,
      responsable_cod: originalOrder.responsable.id,
      // sector: originalOrder.maquina.sector.id,
      tarea: originalOrder.tarea,
      observaciones: originalOrder.observaciones,
      ordenTerciarizacion: originalOrder.ordenTerciarizacion,
      fechaEntrega: originalOrder.fechaEntrega.toString().replace(" ", "T"),
      tipo_cod: originalOrder.tipo.id,

    })
  }

  transformToEdit(dataSourceOrdenes) {
    this.DataOrderToEdit.setValue({
      encargo_cod: dataSourceOrdenes.encargo.id,
      estado: dataSourceOrdenes.estado,
      fechaRealizar: dataSourceOrdenes.fechaRealizar.toString().replace(" ", "T"),
      maquina_cod: dataSourceOrdenes.maquina.maquina_cod,
      pedidoMateriales: dataSourceOrdenes.pedidoMateriales,
      priodidad_cod: dataSourceOrdenes.priodidad.id,
      responsable_cod: dataSourceOrdenes.responsable.id,
      tarea: dataSourceOrdenes.tarea,
      observaciones: dataSourceOrdenes.observaciones,
      ordenTerciarizacion: dataSourceOrdenes.ordenTerciarizacion,
      fechaEntrega: dataSourceOrdenes.fechaEntrega.toString().replace(" ", "T"),
      tipo_cod: dataSourceOrdenes.tipo.id,
    })
  }



  onSave() {
    this.OrdenestrabajoService.updateOrder(this.originalOrder.ordentrabajo_cod, this.form.value).subscribe(order => console.log(order));
    let modal = document.getElementById('myModal');
    modal.style.display = 'none';
    window.location.reload();

  }

  onCancel() {
    this.originalOrder = null;
    this.close.emit();
  }



}
