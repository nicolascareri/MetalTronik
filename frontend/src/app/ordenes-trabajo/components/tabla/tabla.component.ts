import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {MatTableDataSource} from '@angular/material/table';
import {ESTADO_ORDEN, ESTADOTABLE} from 'src/app/core/constants/constants';
import {OrdenestrabajoService} from '../../services/ordenestrabajo.service';
import {PlantaService} from '../../../planta/services/planta.service';
import {SectorService} from '../../../sector/services/sector.service';
import {UserService} from '../../../usuarios/services/user.service';
import {MaquinaService} from '../../../maquina/services/maquina.service';
import {PrioridadesService} from '../../../prioridad/services/prioridades.service';
import {TipoService} from '../../../tipo/services/tipo.service';


@Component({
  selector: 'app-tablaOrdenes',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})


export class TablaOrdenesComponent implements OnInit {

  columnsToDisplay: any = ['edit', 'ordentrabajo_cod', 'maquina.maquina_cod', 'maquina.planta.nombre', 'maquina.sector.descripcion',
    'pedidoMateriales', 'tarea', 'priodidad.nombre', 'tipo.nombre', 'fechaEntrega', 'fechaRealizar',
    'encargo.nombre', 'responsable.nombre', 'estado', 'observaciones', 'ordenTerciarizacion'];

  
  estadosTable: any = ESTADOTABLE;
  estadosForm: any = ESTADO_ORDEN;
  ordenForm: FormGroup;


  dataSourceUsers: any;
  dataSourceOrdenes = new MatTableDataSource();
  dataSourceSectors: any;
  dataSourceMachines: any;
  dataSourcePlants: any;
  dataSourcePrioridades: any;
  dataSourceTipos: any;
  DataOrderToEdit: any;


  @Input() originalOrder: any;
  @Output() close = new EventEmitter();

  form: FormGroup;

  constructor(private OrdenestrabajoService: OrdenestrabajoService,
              private UserService: UserService,
              private SectorService: SectorService,
              private MaquinaService: MaquinaService,
              private PlantaService: PlantaService,
              private PrioridadesService: PrioridadesService,
              private TiposService: TipoService
  )
  {
    this.form = this.createFormGroup();
  }

  createFormGroup(){
    return new FormGroup({
      encargo_cod: new FormControl(''),
      estado: new FormControl(''),
      fechaRealizar: new FormControl(''),
      maquina_cod: new FormControl(''),
      pedidoMateriales: new FormControl(''),
      priodidad_cod: new FormControl(''),
      responsable_cod: new FormControl(''),
      tarea: new FormControl(''),
      observaciones: new FormControl(''),
      ordenTerciarizacion : new FormControl(''),
      fechaEntrega : new FormControl(''),
      tipo_cod: new FormControl(''),

    })
  }


  //////////FILTRO////////////
  applyFilter(filterValue: String) {
    this.dataSourceOrdenes.filter = filterValue.trim().toLowerCase();
    console.log(this.dataSourceOrdenes);
    
  }

  nestedFilterCheck(search, data, key) {
    if (typeof data[key] === 'object') {
      for (const k in data[key]) {
        if (data[key][k] !== null) {
          search = this.nestedFilterCheck(search, data[key], k);
        }
      }
    } else {
      search += data[key];
    }
    return search;
  }
  
  filtro(){
    this.dataSourceOrdenes.filterPredicate = (data, filter: string)  => {
      const accumulator = (currentTerm, key) => {
        return this.nestedFilterCheck(currentTerm, data, key);
      };
      const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
      // Transform the filter by converting it to lowercase and removing whitespace.
      const transformedFilter = filter.trim().toLowerCase();
      return dataStr.indexOf(transformedFilter) !== -1;
    };
  }
  ///////////////////////////
  
  ngOnInit(): void {

    this.filtro();

    this.PrioridadesService.getPrioridades().subscribe(
      (data: any) => {
        this.dataSourcePrioridades = data;
      },
      (error) => {
        console.error(error);
      }
    );

    this.TiposService.getTipos().subscribe(
      (data: any) => {
        this.dataSourceTipos = data;
      },
      (error) => {
        console.error(error);
      }
    );


    this.PlantaService.getPlantas().subscribe(
      (data: any) => { // Success
        this.dataSourcePlants = data;
      },
      (error) => {
        console.error(error);
      }
    );


    this.MaquinaService.getMaquinas().subscribe(
      (data: any) => { // Success
        this.dataSourceMachines = data;

      },
      (error) => {
        console.error(error);
      }
    );


    this.SectorService.getSectores().subscribe(

      (data: any)  => { // Success
        this.dataSourceSectors = data;

      },
      (error) => {
        console.error(error);
      }
    );

    this.UserService.getUsers().subscribe(
      (data: any) => { // Success
        this.dataSourceUsers = data;

      },
      (error) => {
        console.error(error);
      }
    );


    this.OrdenestrabajoService.getAllOrdenes().subscribe(
      (data: any) => { // Success
        this.dataSourceOrdenes.data = data;
        console.log(this.dataSourceOrdenes);
        
      },
      (error) => {
        console.error(error);
      }
    );


    this.closeModal();

    this.transformToEdit(this.dataSourceOrdenes);


  }


  openModal(ordenes){
    let modal = document.getElementById("myModal");
    modal.style.display = "block";
    this.originalOrder = ordenes;
    this.setOriginalValues(this.originalOrder);
  }

  closeModal(){
    window.onclick = function(event) {
      let modal = document.getElementById("myModal");
      if (event.target == modal) {
        modal.style.display = "none";

      }
    }
  }

  setOriginalValues(originalOrder){
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
    ordenTerciarizacion : originalOrder.ordenTerciarizacion,
    fechaEntrega : originalOrder.fechaEntrega.toString().replace(" ", "T"),
    tipo_cod: originalOrder.tipo.id,

    })
  }

  transformToEdit(dataSourceOrdenes){
    this.DataOrderToEdit.setValue({
    encargo_cod: dataSourceOrdenes.encargo.id,
    estado: dataSourceOrdenes.estado,
    fechaRealizar: dataSourceOrdenes.fechaRealizar.toString().replace(" ", "T"),
    maquina_cod: dataSourceOrdenes.maquina.maquina_cod,
    pedidoMateriales: dataSourceOrdenes.pedidoMateriales,
    // planta: dataSourceOrdenes.maquina.planta.id,
    priodidad_cod: dataSourceOrdenes.priodidad.id,
    responsable_cod: dataSourceOrdenes.responsable.id,
    // sector: dataSourceOrdenes.maquina.sector.id,
    tarea: dataSourceOrdenes.tarea,
    observaciones: dataSourceOrdenes.observaciones,
    ordenTerciarizacion : dataSourceOrdenes.ordenTerciarizacion,
    fechaEntrega : dataSourceOrdenes.fechaEntrega.toString().replace(" ", "T"),
    tipo_cod: dataSourceOrdenes.tipo.id,
    })
  }



  onSave() {
    console.log(this.originalOrder.ordentrabajo_cod);
    console.log(this.form.value);
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
