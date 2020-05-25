import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserService } from "../../../usuarios/services/user.service";
import { OrdenestrabajoService } from "../../services/ordenestrabajo.service";
import { SectorService } from "../../services/sector.service";
import { MaquinaService } from "../../services/maquina.service";
import { PLANTAS, PRIORIDADES, ESTADO_ORDEN, TIPO } from "src/app/core/constants/constants";
import { PlantaService } from "../../services/planta.service";



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
              private SectorService: SectorService, private MaquinaService: MaquinaService, private PlantaService: PlantaService) {
    this.ordenForm = this.createFormGroup();
   }

  ngOnInit(): void {

    // var inputValue = (<HTMLInputElement>document.getElementById('#inputDate')).value;
    // this.ordenForm.value.fechaRealizar = this.datePipe.transform(new Date(), 'dd-mm-2020 hh:mm');
    // inputValue.value = '10-10-2020 10:10';

    
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
        this.OrdenestrabajoService.postOrder(this.ordenForm).subscribe(order => console.log(order)
        );

      alert("Orden enviada");
        
      }
      
      

}
