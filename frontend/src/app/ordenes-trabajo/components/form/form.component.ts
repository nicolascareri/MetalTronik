import { Component, OnInit, Input } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserService } from "../../../usuarios/services/user.service";
import { OrdenestrabajoService } from "../../services/ordenestrabajo.service";
import { SectorService } from "../../services/sector.service";
import { MaquinaService } from "../../../maquina/services/maquina.service";
import { PLANTAS, PRIORIDADES, ESTADO_ORDEN, TIPO } from "src/app/core/constants/constants";
import { PlantaService } from "../../services/planta.service";
import { EditOrderComponent } from "../edit-order/edit-order.component";
import { Router } from "@angular/router"

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {

  
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

  // createFormEdit(){
  //   return new FormGroup({
  //     encargo_cod: this.orderToEdit.encargo_cod,
  //     estado: this.orderToEdit.estado,
  //     fechaRealizar: this.orderToEdit.fechaRealizar,
  //     maquina_cod: this.orderToEdit.maquina_cod,
  //     pedidoMateriales: this.orderToEdit.pedidoMateriales,
  //     planta_cod: this.orderToEdit.planta_cod,
  //     priodidad: this.orderToEdit.priodidad,
  //     responsable_cod: this.orderToEdit.responsable_cod,
  //     sector_cod: this.orderToEdit.sector_cod,
  //     tarea: this.orderToEdit.tarea,
  //     observaciones: this.orderToEdit.observaciones,
  //     ordenTerciarizacion : this.orderToEdit.ordenTerciarizacion,
  //     fechaEntrega : this.orderToEdit.fechaEntrega,
  //     tipo: this.orderToEdit.tipo,
      
  //   })
  // }



  //@Input()titulo : string = "Nueva orden";
  // @Input()originalOrder : OrdenestrabajoService;
  //editComponent : EditOrderComponent;
  // orderToEdit : any = this.editComponent.originalOrder;
  // editForm: FormGroup;

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


  constructor(private OrdenestrabajoService: OrdenestrabajoService, private UserService: UserService,
              private SectorService: SectorService, private MaquinaService: MaquinaService, private PlantaService: PlantaService, private router:Router) {
    this.ordenForm = this.createFormGroup();
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
      
      
    }
      
      resetForm() {
        this.ordenForm.reset();
      }
      
      saveForm() {
        console.log(this.ordenForm.value);
        this.OrdenestrabajoService.postOrder(this.ordenForm).subscribe(
          order => alert("Se ha creado la orden nro: " + order.ordentrabajo_cod)
        );
        this.router.navigate(['main/ordenes'])
      
        
      }
 
      

}
