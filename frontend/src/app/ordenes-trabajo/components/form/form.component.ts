import { Component, OnInit, AfterViewInit } from '@angular/core';
import { first } from "rxjs/operators"
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { OrdenestrabajoService } from '../../services/ordenestrabajo.service';
import { MaquinaService } from '../../../maquina/services/maquina.service';
import { UserService } from '../../../usuarios/services/user.service';
import { PrioridadesService } from '../../../prioridad/services/prioridades.service';
import { TipoService } from '../../../tipo/services/tipo.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit, AfterViewInit {


  constructor(private OrdenestrabajoService: OrdenestrabajoService,
    private UserService: UserService,
    private MaquinaService: MaquinaService,
    private PrioridadesService: PrioridadesService,
    private TipoService: TipoService,
    private router: Router,
    private route: ActivatedRoute) {
    this.ordenForm = this.createFormGroup();
  }
  ordenForm: FormGroup;
  dataSourceUsers: any;
  dataSourceOrdenes: any;
  dataSourceSectors: any;
  dataSourceMachines: any;
  dataSourcePlants: any;
  dataSourceTipos: any;
  dataSourcePrioridades: any;
  public ordenId: any;
  public mode = 'add';

  ngOnInit(): void {

    this.ordenId = this.route.snapshot.params.id;
    this.getMaquinas();
    this.getPrioridades();
    this.getTipos();
    this.getUsuarios();
    this.getOrdenes();

  }
  ngAfterViewInit(): void {
    if (this.ordenId) {
      this.getOrden(this.ordenId);
    }
  }
  getOrden(id) {
    this.OrdenestrabajoService.getOrder(id).pipe(first()).subscribe(
      orden => {
        this.loadOrden(orden);
      }
    )
  }
  loadOrden(orden) {
    this.mode = "edit";
    console.log(orden);
    
    this.ordenForm.controls.encargo_cod.setValue(orden.encargo.id);
    this.ordenForm.controls.estado.setValue(orden.estado);
    this.ordenForm.controls.fechaEntrega.setValue(orden.fechaEntrega.replace(' ', 'T'))
    this.ordenForm.controls.fechaRealizar.setValue(orden.fechaRealizar.replace(' ', 'T'))
    this.ordenForm.controls.maquina_cod.setValue(orden.maquina.id)
    this.ordenForm.controls.observaciones.setValue(orden.observaciones)
    this.ordenForm.controls.ordenTerciarizacion.setValue(orden.ordenTerciarizacion)
    this.ordenForm.controls.pedidoMateriales.setValue(orden.pedidoMateriales)
    this.ordenForm.controls.prioridad_cod.setValue(orden.prioridad.id)
    this.ordenForm.controls.responsable_cod.setValue(orden.responsable.id)
    this.ordenForm.controls.tarea.setValue(orden.tarea)
    this.ordenForm.controls.tipo_cod.setValue(orden.tipo.id)
  }
  getOrdenes() {
    this.OrdenestrabajoService.getAllOrdenes().subscribe(
      (data: any) => { // Success
        this.dataSourceOrdenes = data;
      },
      (error) => {
        console.error(error);
      }
    );
  }
  getUsuarios() {
    this.UserService.getUsers().subscribe(
      (data: any) => {
        this.dataSourceUsers = data.map(
          val => {
            return {
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

  getPrioridades() {
    this.PrioridadesService.getPrioridades().subscribe(
      (data: any) => {
        this.dataSourcePrioridades = data.map(
          val => {
            return {
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
  }

  getMaquinas() {
    this.MaquinaService.getMaquinas().subscribe(
      (data: any) => {
        this.dataSourceMachines = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.maquina_cod
            }
          }
        );
      },
      (error) => {
        console.error(error);
      }
    );
  }
  getTipos() {
    this.TipoService.getTipos().subscribe(
      (data: any) => {
        this.dataSourceTipos = data.map(
          val => {
            return {
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
  }
  resetForm() {
    this.ordenForm.reset();
  }

  saveForm() {
    if (this.mode === 'add') {
      this.OrdenestrabajoService.postOrder(this.ordenForm).subscribe(
        order => alert("Se ha creado la orden nro: " + order.ordentrabajo_cod)
      );
    } else {
      this.OrdenestrabajoService.updateOrder(this.ordenId, this.ordenForm).subscribe(
        order => {
          console.log(order);
        });
    }
    this.router.navigate(['main/ordenes'])
  }
  createFormGroup() {
    return new FormGroup({
      encargo_cod: new FormControl(''),
      estado: new FormControl(''),
      fechaRealizar: new FormControl(''),
      maquina_cod: new FormControl(''),
      pedidoMateriales: new FormControl(''),
      prioridad_cod: new FormControl(''),
      responsable_cod: new FormControl(''),
      tarea: new FormControl(''),
      observaciones: new FormControl(''),
      ordenTerciarizacion: new FormControl(''),
      fechaEntrega: new FormControl(''),
      tipo_cod: new FormControl(''),

    })
  }
}
