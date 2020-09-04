import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {MantenimientoCorrectivoService} from '../../services/mantenimiento-correctivo.service';
import {OrdenestrabajoService} from '../../../ordenes-trabajo/services/ordenestrabajo.service';
import {MaquinaService} from '../../../maquina/services/maquina.service';
import {UserService} from '../../../usuarios/services/user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-form-mantenimiento-correctivo',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})

export class FormMantenimientoCorrectivoComponent implements OnInit {

  public mantenimientoCorrectivoForm: FormGroup;
  public dataSourceOrders: any;
  public dataSourceMachines: any;
  public dataSourceUsers: any;
  public mantenimientoId: any;
  public mode = 'add';

  constructor(private MantenimientoCorrectivoService: MantenimientoCorrectivoService,
              private OrdenestrabajoService: OrdenestrabajoService,
              private MaquinaService: MaquinaService,
              private UserService: UserService,
              private route: ActivatedRoute,
              private router: Router,
  )
  {
    this.mantenimientoCorrectivoForm = this.createFormGroup();
  }


  ngOnInit(): void {
    this.mantenimientoId = this.route.snapshot.params.id;
    this.getOrdenes();
    this.getMaquinas();
    this.getUsuarios();
  }

  ngAfterViewInit(): void {
    if (this.mantenimientoId) {
      this.getMantenimiento(this.mantenimientoId);
    }
  }

  getMantenimiento(id){
    this.MantenimientoCorrectivoService.getMantenimiento(id).pipe(first()).subscribe(
      mantenimiento => {
        this.loadMantenimiento(mantenimiento);
      }
    )
  }

  loadMantenimiento(mantenimiento) {
    this.mode = "edit";
    console.log(mantenimiento);
    this.mantenimientoCorrectivoForm.controls.nrocorrectivo.setValue(mantenimiento.nrocorrectivo);
    this.mantenimientoCorrectivoForm.controls.tipofalla.setValue(mantenimiento.tipofalla);
    this.mantenimientoCorrectivoForm.controls.encargo1_cod.setValue(mantenimiento.encargo1_cod.id);
    this.mantenimientoCorrectivoForm.controls.encargo2_cod.setValue(mantenimiento.encargo2_cod.id);
    this.mantenimientoCorrectivoForm.controls.encargo3_cod.setValue(mantenimiento.encargo3_cod.id);
    this.mantenimientoCorrectivoForm.controls.fechainicio.setValue(mantenimiento.fechainicio.replace(' ', 'T'));
    this.mantenimientoCorrectivoForm.controls.fechaFin.setValue(mantenimiento.fechaFin.replace(' ', 'T'));
    this.mantenimientoCorrectivoForm.controls.maquina_cod.setValue(mantenimiento.maquina_cod.id);
    this.mantenimientoCorrectivoForm.controls.ordentrabajo_cod.setValue(mantenimiento.ordentrabajo_cod);
    this.mantenimientoCorrectivoForm.controls.observaciones.setValue(mantenimiento.observaciones);
  }

  getOrdenes(){
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
  }

  getMaquinas(){
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
  }

  getUsuarios(){
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

  resetForm() {
    this.mantenimientoCorrectivoForm.reset();
  }

  saveForm() {
    console.log(this.mantenimientoCorrectivoForm.value);
    this.MantenimientoCorrectivoService.postMantenimientoCorrectivo(this.mantenimientoCorrectivoForm).subscribe(
      maquina => alert("Se el mantenimiento nro: " + maquina.id)
    );
    this.router.navigate(['main/mantenimientosCorrectivos'])
  }

}
