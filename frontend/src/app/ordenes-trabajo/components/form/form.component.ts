import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {OrdenestrabajoService} from '../../services/ordenestrabajo.service';
import {MaquinaService} from '../../../maquina/services/maquina.service';
import {UserService} from '../../../usuarios/services/user.service';
import {PrioridadesService} from '../../../prioridad/services/prioridades.service';
import {TipoService} from '../../../tipo/services/tipo.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {


  constructor(private OrdenestrabajoService: OrdenestrabajoService,
              private UserService: UserService,
              private MaquinaService: MaquinaService,
              private PrioridadesService: PrioridadesService,
              private TipoService: TipoService,
              private router: Router) {
  }


  ordenForm: FormGroup;
  dataSourceUsers: any;
  dataSourceOrdenes: any;
  dataSourceSectors: any;
  dataSourceMachines: any;
  dataSourcePlants: any;
  dataSourceTipos: any;
  dataSourcePrioridades: any;



  ngOnInit(): void {

    this.ordenForm = this.createFormGroup();
    this.MaquinaService.getMaquinas().subscribe(

      (data: any)  => { 
        this.dataSourceMachines = data.map(
          val => { return {
              "id": val.id,
              "descripcion": val.maquina_cod
            }
          }
        );
        console.log(this.dataSourceMachines);
      },
      (error) => {
        console.error(error);
      }

    );

    this.PrioridadesService.getPrioridades().subscribe(
      (data: any) => {
        this.dataSourcePrioridades = data.map(
          val => { return {
              "id": val.id,
              "descripcion": val.nombre
            }
          }
        );
      },
      (error) => {
        console.error(error);
      }
    );

    this.TipoService.getTipos().subscribe(
      (data: any) => {
        this.dataSourceTipos = data.map(
          val => { return {
              "id": val.id,
              "descripcion": val.nombre
            }
          }
        );
      },
      (error) => {
        console.error(error);
      }
    );


    this.UserService.getUsers().subscribe(

      (data: any)  => { 
        this.dataSourceUsers = data.map(
          val => { return {
              "id": val.id,
              "descripcion": val.nombre + " " + val.apellido
            }
          }
        );
        console.log(this.dataSourceUsers);
      },
      (error) => {
        console.error(error);
      }

    );


    this.OrdenestrabajoService.getAllOrdenes().subscribe(
      (data: any)  => { 
        this.dataSourceOrdenes = data;
      },
      (error) => {
        console.error(error);
      }
    );


  }

  createFormGroup(){
    return new FormGroup({
      encargo_cod: new FormControl(''),
      estado: new FormControl(''),
      fechaRealizar: new FormControl(''),
      maquina_cod: new FormControl(''),
      pedidoMateriales: new FormControl(''),
      prioridad_cod: new FormControl(''),
      responsable_cod: new FormControl(''),
      tarea: new FormControl(''),
      observaciones: new FormControl('', Validators.required),
      ordenTerciarizacion : new FormControl(''),
      fechaEntrega : new FormControl(''),
      tipo_cod: new FormControl(''),

    })
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
