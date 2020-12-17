import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {MantenimientoCorrectivoService} from '../../services/mantenimiento-correctivo.service';
import {OrdenestrabajoService} from '../../../ordenes-trabajo/services/ordenestrabajo.service';
import {MaquinaService} from '../../../maquina/services/maquina.service';
import {UserService} from '../../../usuarios/services/user.service';
import { ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { MessageService } from "../../../core/service/message.service";
import { TipoService } from "../../../tipo/services/tipo.service";
import { ParteService } from "../../../maquina/services/parte.service";

@Component({
  selector: 'app-form-mantenimiento-correctivo',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})

export class FormMantenimientoCorrectivoComponent implements OnInit {

  public mantenimientoCorrectivoForm: FormGroup = new FormGroup({
    encargo1_cod: new FormControl(''),
    encargo2_cod: new FormControl(''),
    encargo3_cod: new FormControl(''),
    fechaFin: new FormControl(''),
    fechainicio: new FormControl(''),
    horasProduccionAfectadas: new FormControl(''),
    maquina_cod: new FormControl(''),
    parte_cod: new FormControl(''),
    nrocorrectivo: new FormControl(''),
    observaciones: new FormControl(''),
    ordenTrabajo_cod: new FormControl(''),
    tipo_id: new FormControl('')
  })
  public dataSourceOrders: any;
  public dataSourceMachines: any;
  public dataSourcePartes: any;
  public dataSourceUsers: any;
  public dataSourceTipos: any;
  public mantenimientoId: any;
  public maquinaId: any;
  public mode = 'add';
  public section = 'Nuevo mantenimiento correctivo';
  public buttonName = 'Crear mantenimiento correctivo'
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "Mantenimiento creado correctamente";

  constructor(private MantenimientoCorrectivoService: MantenimientoCorrectivoService,
              private OrdenestrabajoService: OrdenestrabajoService,
              private MaquinaService: MaquinaService,
              private UserService: UserService,
              private route: ActivatedRoute,
              private MessageService: MessageService,
              private TipoService: TipoService,
              private ParteService: ParteService
  )
  {}


  ngOnInit(): void {
    this.mantenimientoId = this.route.snapshot.params.id;
    this.getOrdenes();
    this.getMaquinas();
    this.getUsuarios();
    this.getTipos('Correctivos');
  }

  ngAfterViewInit(): void {
    if (this.mantenimientoId) {
      this.getMantenimiento(this.mantenimientoId);
    }
  }

  showSuccess(){
    this.MessageService.showSuccess({
      title: this.messageTitleSuccess,
      body: this.messageBody
    });
  }

  showError(message){
    this.MessageService.showError({
      title: this.messageTitleError,
      body: message.errors ? message.errors[0].defaultMessage + ". campo: " + message.errors[0].field + ", Valor rechazado: " + message.errors[0].rejectedValue : message.error
    })
  }

  getMantenimiento(id){
    this.MantenimientoCorrectivoService.getMantenimiento(id).pipe(first()).subscribe(
      mantenimiento => {
        this.loadMantenimiento(mantenimiento);
      }
    )
  }

  loadMantenimiento(mantenimiento) {
    this.getPartes(mantenimiento.maquina.id);
    this.mode = "edit";
    this.section = 'Editar mantenimiento correctivo';
    this.buttonName = 'Confirmar cambios';
    this.mantenimientoCorrectivoForm.controls.nrocorrectivo.setValue(mantenimiento.nrocorrectivo);
    this.mantenimientoCorrectivoForm.controls.tipo_id.setValue(mantenimiento.tipo_id);
    this.mantenimientoCorrectivoForm.controls.encargo1_cod.setValue(mantenimiento.encargo1.id);
    this.mantenimientoCorrectivoForm.controls.encargo2_cod.setValue(mantenimiento.encargo2 ? mantenimiento.encargo2.id: null);
    this.mantenimientoCorrectivoForm.controls.encargo3_cod.setValue(mantenimiento.encargo3 ? mantenimiento.encargo3.id: null);
    this.mantenimientoCorrectivoForm.controls.fechainicio.setValue(mantenimiento.fechainicio.replace(' ', 'T'));
    this.mantenimientoCorrectivoForm.controls.fechaFin.setValue(mantenimiento.fechaFin.replace(' ', 'T'));
    this.mantenimientoCorrectivoForm.controls.maquina_cod.setValue(mantenimiento.maquina.id);
    this.mantenimientoCorrectivoForm.controls.parte_cod.setValue(mantenimiento.parte.id);
    this.mantenimientoCorrectivoForm.controls.ordenTrabajo_cod.setValue(mantenimiento.ordenTrabajo.ordentrabajo_cod);
    this.mantenimientoCorrectivoForm.controls.observaciones.setValue(mantenimiento.observaciones);
    this.mantenimientoCorrectivoForm.controls.horasProduccionAfectadas.setValue(mantenimiento.horasProduccionAfectadas);
  }

  getOrdenes(){
    this.OrdenestrabajoService.getAllOrdenes().subscribe(
      (data: any)  => { // Success
        this.dataSourceOrders = data.map(
          val => { return {
              "id": val.ordentrabajo_cod,
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

  getPartes(id){
    this.ParteService.getByMaquina(id).subscribe(
      (data: any) => {
        this.dataSourcePartes = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.nombre + " " + val.codigo
            }
          }
        );
      },
      (error) => {
        console.log(error.error);
      }
    );
  }
  
  getInAddMode(id){
    this.maquinaId =  id.split(" ");
    id =  this.maquinaId[1];
    this.getPartes(id);
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

  getTipos(tipo){
    this.TipoService.getTipos(tipo).subscribe(
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
        console.log(error.error);
      }
    );
  }

  saveForm() {
    if (this.mode === 'add') {
      this.MantenimientoCorrectivoService.postMantenimientoCorrectivo(this.mantenimientoCorrectivoForm).subscribe(
        mantenimiento => {
          this.showSuccess();
        },
        error => this.showError(error.error)
      );
    } else {
      this.getPartes(this.maquinaId);
      this.MantenimientoCorrectivoService.update(this.mantenimientoId, this.mantenimientoCorrectivoForm).subscribe(
        mantenimiento => {
          this.messageBody = "El mantenimiento se edito correctamente"
          this.showSuccess();
        },
        error => this.showError(error.error)
      );
    }
  }

}
