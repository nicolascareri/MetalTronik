import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {MantenimientoCorrectivoService} from '../../services/mantenimiento-correctivo.service';
import {OrdenestrabajoService} from '../../../ordenes-trabajo/services/ordenestrabajo.service';
import {MaquinaService} from '../../../maquina/services/maquina.service';
import {UserService} from '../../../usuarios/services/user.service';

@Component({
  selector: 'app-form-mantenimiento-correctivo',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormMantenimientoCorrectivoComponent implements OnInit {

  mantenimientoCorrectivoForm: FormGroup;
  dataSourceOrders: any;
  dataSourceMachines: any;
  dataSourceUsers: any;

  constructor(private MantenimientoCorrectivoService: MantenimientoCorrectivoService,
              private OrdenestrabajoService: OrdenestrabajoService,
              private MaquinaService: MaquinaService,
              private UserService: UserService
  )
  {
    this.mantenimientoCorrectivoForm = this.createFormGroup();
  }

  createFormGroup() {
    return new FormGroup({
      encargo1_cod: new FormControl(''),
      encargo2_cod: new FormControl(''),
      encargo3_cod: new FormControl(''),
      fechaFin: new FormControl(''),
      fechainicio: new FormControl(''),
      horasProduccionAfectadas: new FormControl(''),
      maquina_cod: new FormControl(''),
      nrocorrectivo: new FormControl(''),
      observaciones: new FormControl(''),
      ordenTrabajo_cod: new FormControl(''),
      tipofalla: new FormControl('')
    })
  }

  ngOnInit(): void {

    this.OrdenestrabajoService.getAllOrdenes().subscribe(

      (data: any)  => { // Success
        this.dataSourceOrders = data.map(
          val => { return {
              "id": val.id,
              "descripcion": val.ordentrabajo_cod
            }
          }
        );
        console.log(this.dataSourceOrders);
      },
      (error) => {
        console.error(error);
      }

    );

    this.MaquinaService.getMaquinas().subscribe(

      (data: any)  => { // Success
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

    this.UserService.getUsers().subscribe(
      (data: any) => {
        this.dataSourceUsers = data.map(
          val => { return {
              "id": val.id,
              "descripcion": val.nombre + " " + val.apellido
            }
          }
        );
        
      },
      (error) => {
        console.error(error);
      }

    );


  }

  resetForm() {
    this.mantenimientoCorrectivoForm.reset();
  }

  saveForm() {
    console.log(this.mantenimientoCorrectivoForm.value);
    this.MantenimientoCorrectivoService.postMantenimientoCorrectivo(this.mantenimientoCorrectivoForm).subscribe(
      maquina => alert("Se ha creado la maquina numero: " + maquina.id)
    );
    //this.router.navigate(['main/ordenes'])


  }

}
