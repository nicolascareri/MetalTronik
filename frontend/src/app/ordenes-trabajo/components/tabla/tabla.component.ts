import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { UserService } from "../../../usuarios/services/user.service";
import {MatTableDataSource} from '@angular/material/table';
import {OrdenestrabajoService} from '../../services/ordenestrabajo.service';
import { TIPOTABLE , PLANTATABLE, PRIORIDADESTABLE, ESTADOTABLE} from "src/app/core/constants/constants";
import { FormBuilder, FormGroup,  FormControl } from "@angular/forms";
import { SectorService } from "../../services/sector.service";
import { MaquinaService } from "../../services/maquina.service";
import { PRIORIDADES, ESTADO_ORDEN, TIPO } from "src/app/core/constants/constants";
import { PlantaService } from "../../services/planta.service";


@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})



export class TablaComponent implements OnInit {

  // ordenes: any = [];

  columnsToDisplay: any = ['edit','ordentrabajo_cod','planta', 'sector', 'maquina.maquina_cod', 
                           'pedidoMateriales', 'tarea', 'priodidad', 'tipo', 'fechaEntrega', 'fechaRealizar',
                           'encargo.nombre', 'responsable.nombre', 'estado', 'observaciones', 'ordenTerciarizacion'];

  // plantas: any = PLANTATABLE;
  tiposTable: any = TIPOTABLE;
  prioridadesTable: any = PRIORIDADESTABLE;
  estadosTable: any = ESTADOTABLE;

  estadosForm: any = ESTADO_ORDEN;
  prioridadesForm: any = PRIORIDADES;
  tipoForm: any = TIPO;

  ordenForm: FormGroup;

 
  dataSourceUsers: any;
  dataSourceOrdenes: any;
  dataSourceSectors: any;
  dataSourceMachines: any;
  dataSourcePlants: any;
  



  @Input() originalOrder: any; 
  @Output() close = new EventEmitter();

  form: FormGroup;

  createFormGroup(){
    return new FormGroup({
      encargo_cod: new FormControl(''),
      estado: new FormControl(''),
      fechaRealizar: new FormControl(''),
      maquina_cod: new FormControl(''),
      pedidoMateriales: new FormControl(''),
      planta_cod: new FormControl(''),
      priodidad: new FormControl(''),
      responsable_cod: new FormControl(''),
      sector_cod: new FormControl(''),
      tarea: new FormControl(''),
      observaciones: new FormControl(''),
      ordenTerciarizacion : new FormControl(''),
      fechaEntrega : new FormControl(''),
      tipo: new FormControl(''),
      
    })
  }

  
  



 

  


  constructor( private OrdenestrabajoService: OrdenestrabajoService,
    private UserService: UserService,
    private SectorService: SectorService, 
    private MaquinaService: MaquinaService, 
    private PlantaService: PlantaService,
    private fb: FormBuilder
  ) {
    this.form = this.createFormGroup();
    }

  
  

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSourceOrdenes.filter = filterValue.trim().toLowerCase();
    console.log(this.dataSourceOrdenes);
    console.log(this.dataSourceOrdenes.filter);
    
  }


  ngOnInit(): void {

    this.PlantaService.getPlantas().subscribe(

      (data: any)  => { // Success
        this.dataSourcePlants = data;
        console.log(this.dataSourcePlants);
      },
      (error) => {
        console.error(error);
      }

    );


    this.MaquinaService.getMaquinas().subscribe(

      (data: any)  => { // Success
        this.dataSourceMachines = data;
        console.log(this.dataSourceMachines);
      },
      (error) => {
        console.error(error);
      }

    );

    this.SectorService.getSectores().subscribe(

      (data: any)  => { // Success
        this.dataSourceSectors = data;
        console.log(this.dataSourceSectors);
      },
      (error) => {
        console.error(error);
      }

    );

    this.UserService.getUsers().subscribe(

      (data: any)  => { // Success
        this.dataSourceUsers = data;
        console.log(this.dataSourceUsers);
      },
      (error) => {
        console.error(error);
      }

    );
    
    
    this.OrdenestrabajoService.getAllOrdenes().subscribe(
      
      (data: any)  => { // Success
        this.dataSourceOrdenes = data;
        console.log(this.dataSourceOrdenes);
      },
      (error) => {
        console.error(error);
      }
      
      );
      
      
    this.closeModal();


  }

  openModal(ordenes){
    let modal = document.getElementById("myModal");
    modal.style.display = "block";
    this.originalOrder = ordenes;
    console.log(this.originalOrder);
    this.setOriginalValues(this.originalOrder);
    console.log(this.form);
    
    
}

closeModal(){
  window.onclick = function(event) {
    let modal = document.getElementById("myModal");
    if (event.target == modal) {
      modal.style.display = "none";
      console.log(this.originalOrder);
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
    planta_cod: originalOrder.planta.id,
    priodidad: originalOrder.priodidad,
    responsable_cod: originalOrder.responsable.id,
    sector_cod: originalOrder.sector.id,
    tarea: originalOrder.tarea,
    observaciones: originalOrder.observaciones,
    ordenTerciarizacion : originalOrder.ordenTerciarizacion,
    fechaEntrega : originalOrder.fechaEntrega.toString().replace(" ", "T"),
    tipo: originalOrder.tipo,

  })
}



onSave() {
  console.log(this.originalOrder.ordentrabajo_cod);
  console.log(this.form.value);
  this.OrdenestrabajoService.updateOrder(this.originalOrder.ordentrabajo_cod, this.form.value).subscribe(order => console.log(order));
  let modal = document.getElementById("myModal");
  modal.style.display = "none";
  window.location.reload();
  
}

onCancel() {
  this.originalOrder = null;
  this.close.emit();
}

 

}
