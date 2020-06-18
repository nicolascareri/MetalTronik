import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup,  FormControl } from "@angular/forms";
import { OrdenestrabajoService } from "./../../services/ordenestrabajo.service";
import { SectorService } from "../../services/sector.service";
import { MaquinaService } from "../../../maquina/services/maquina.service";
import { PLANTAS, PRIORIDADES, ESTADO_ORDEN, TIPO } from "src/app/core/constants/constants";
import { PlantaService } from "../../services/planta.service";
import { UserService } from "../../../usuarios/services/user.service";


@Component({
  selector: 'app-edit-order',
  templateUrl: './edit-order.component.html',
  styleUrls: ['./edit-order.component.scss']
})

export class EditOrderComponent implements OnInit {

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

  
  



  plantas: any = PLANTAS;
  prioridades: any = PRIORIDADES;
  estados: any = ESTADO_ORDEN;
  tipo: any = TIPO;
  
  ordenForm: FormGroup;
  users: any;
  orders: any;
  sectors: any;
  machines: any;
  plants: any;
  dataSource: any;
  dataSourceOrdenes: any;
  dataSourceSectors: any;
  dataSourceMachines: any;
  dataSourcePlants: any;





  constructor(

    private OrdenestrabajoService: OrdenestrabajoService,
    private UserService: UserService,
    private SectorService: SectorService, 
    private MaquinaService: MaquinaService, 
    private PlantaService: PlantaService,
    private fb: FormBuilder

    ) {
      this.form = this.createFormGroup();
    }

  ngOnInit(): void {

    this.PlantaService.getPlantas().subscribe(

      (data: any)  => { // Success
        this.plants = data;
        this.dataSourcePlants = this.plants;
        console.log(this.dataSourcePlants);
      },
      (error) => {
        console.error(error);
      }

    );


    this.MaquinaService.getMaquinas().subscribe(

      (data: any)  => { // Success
        this.machines = data;
        this.dataSourceMachines = this.machines;
        console.log(this.dataSourceMachines);
      },
      (error) => {
        console.error(error);
      }

    );

    this.SectorService.getSectores().subscribe(

      (data: any)  => { // Success
        this.sectors = data;
        this.dataSourceSectors = this.sectors;
        console.log(this.dataSourceSectors);
      },
      (error) => {
        console.error(error);
      }

    );

    this.UserService.getUsers().subscribe(

      (data: any)  => { // Success
        this.users = data;
        this.dataSource = this.users;
        console.log(this.dataSource);
      },
      (error) => {
        console.error(error);
      }

    );
    
    
    this.OrdenestrabajoService.getAllOrdenes().subscribe(
      
      (data: any)  => { // Success
        this.orders = data;
        this.dataSourceOrdenes = this.orders;
        console.log(this.dataSourceOrdenes);
      },
      (error) => {
        console.error(error);
      }
      
      );
      
      
    this.closeModal();
    
    
  }

  openModal(){
      let modal = document.getElementById("myModal");
      modal.style.display = "block";
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


  // setOriginalValues(originalOrder){
  //   this.form.controls['encargo_cod'].setValue(originalOrder.encargo.id);
    
  // }
  setOriginalValues(originalOrder){
    this.form.setValue({
      encargo_cod: originalOrder.encargo.id,
      estado: originalOrder.estado,
      fechaRealizar: originalOrder.fechaRealizar,
      maquina_cod: originalOrder.maquina.maquina_cod,
      pedidoMateriales: originalOrder.pedidoMateriales,
      planta_cod: originalOrder.planta.nombre,
      priodidad: originalOrder.priodidad,
      responsable_cod: originalOrder.responsable.id,
      sector_cod: originalOrder.sector.id,
      tarea: originalOrder.tarea,
      observaciones: originalOrder.observaciones,
      ordenTerciarizacion : originalOrder.ordenTerciarizacion,
      fechaEntrega : originalOrder.fechaEntrega,
      tipo: originalOrder.tipo,

    })
  }

  

  onSave() {
    console.log(this.originalOrder.ordentrabajo_cod);
    console.log(this.form.value);
    this.OrdenestrabajoService.updateOrder(this.originalOrder.ordentrabajo_cod, this.form.value).subscribe(order => console.log(order));
    //this.onCancel();
  }

  onCancel() {
    this.originalOrder = null;
    this.close.emit();
  }

}
