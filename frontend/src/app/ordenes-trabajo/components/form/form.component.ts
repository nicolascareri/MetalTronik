import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';

import {OrdenestrabajoService} from '../../services/ordenestrabajo.service';
// import { SectorService } from "../../services/sector.service";
import {MaquinaService} from '../../../maquina/services/maquina.service';
import {UserService} from '../../../usuarios/services/user.service';
import {PrioridadesService} from '../../../prioridad/services/prioridades.service';
import {TipoService} from '../../../tipo/services/tipo.service';
// import { PlantaService } from "../../services/planta.service";
import {Router} from '@angular/router';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {


  constructor(private OrdenestrabajoService: OrdenestrabajoService,
              private UserService: UserService,
              // private SectorService: SectorService,
              private MaquinaService: MaquinaService,
              // private PlantaService: PlantaService,
              private PrioridadesService: PrioridadesService,
              private TipoService: TipoService,
              private router: Router) {
    this.ordenForm = this.createFormGroup();
  }


  // plantas: any = PLANTAS;
  // prioridades: any = PRIORIDADES;
  // estados: any = ESTADO_ORDEN;
  // tipo: any = TIPO;

  ordenForm: FormGroup;
  // users: any;
  // orders: any;
  // sectors: any;
  // machines: any;
  // plants: any;
  dataSourceUsers: any;
  dataSourceOrdenes: any;
  dataSourceSectors: any;
  dataSourceMachines: any;
  dataSourcePlants: any;
  dataSourceTipos: any;
  dataSourcePrioridades: any;

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

  ngOnInit(): void {


    // this.PlantaService.getPlantas().subscribe(

    //   (data: any)  => { // Success
    //     this.dataSourcePlants = data;
    //     console.log(this.dataSourcePlants);
    //   },
    //   (error) => {
    //     console.error(error);
    //   }

    // );


    this.MaquinaService.getMaquinas().subscribe(

      (data: any)  => { // Success
        this.dataSourceMachines = data;
        console.log(this.dataSourceMachines);
      },
      (error) => {
        console.error(error);
      }

    );

    this.PrioridadesService.getPrioridades().subscribe(
      (data: any) => {
        this.dataSourcePrioridades = data;
      },
      (error) => {
        console.error(error);
      }
    );

    this.TipoService.getTipos().subscribe(
      (data: any) => {
        this.dataSourceTipos = data;
      },
      (error) => {
        console.error(error);
      }
    );

    // this.SectorService.getSectores().subscribe(

    //   (data: any)  => { // Success
    //     this.sectors = data;
    //     this.dataSourceSectors = this.sectors;
    //     console.log(this.dataSourceSectors);
    //   },
    //   (error) => {
    //     console.error(error);
    //   }

    // );

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
