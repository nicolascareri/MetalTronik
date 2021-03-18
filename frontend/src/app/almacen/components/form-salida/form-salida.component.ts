import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { MessageService } from "../../../core/service/message.service";
import { SalidaService } from "../../services/salida.service";
import { AlmacenService } from "../../services/almacen.service";
import { UserService } from "../../../usuarios/services/user.service";
import { TipoService } from '../../../tipo/services/tipo.service';

@Component({
  selector: 'app-form-salida',
  templateUrl: './form-salida.component.html',
  styleUrls: ['./form-salida.component.scss']
})

export class FormSalidaComponent implements OnInit {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  public dataSourceSectors: any;
  public dataSourceSalida;
  public dataSourceRepuestos;
  public dataSourceUsuarios;
  public salidaId: any;
  public mode = 'add';
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "La salida se ha creado correctamente";
  public salidaForm: FormGroup = new FormGroup({
    cantidad: new FormControl(''),
    fecha: new FormControl(''),
    repuesto_id: new FormControl(''),
    sector_id: new FormControl(''),
    solicitante_id: new FormControl(''),
  });

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  constructor(private SalidaService: SalidaService,
              private route: ActivatedRoute,
              private MessageService: MessageService,
              private AlmacenService: AlmacenService,
              private TipoService: TipoService,
              private UserService: UserService,
              private router: Router) { }

  ngOnInit(): void {
    this.salidaId = this.route.snapshot.params.id;
    this.getRepuestos();
    this.getUsuarios();
    this.getSectores();
  }

  ngAfterViewInit(): void {
    if (this.salidaId) {
      this.getSalida(this.salidaId);
    }
  }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  loadSalida(salida) {
    this.mode = "edit";
    this.salidaForm.controls.cantidad.setValue(salida.cantidad);
    this.salidaForm.controls.fecha.setValue(salida.fecha);
    this.salidaForm.controls.repuesto_id.setValue(salida.repuesto_id);
    this.salidaForm.controls.sector_id.setValue(salida.sector_id);
    this.salidaForm.controls.solicitante_id.setValue(salida.solicitante_id);
  }

  saveForm() {
    if (this.mode === 'add') {
      this.SalidaService.postSalida(this.salidaForm).subscribe(
        salida => {
          this.showSuccess();
          this.router.navigate(['main/almacen/salida']);
        },
        error => this.showError(error.error)
      );
    } else {
      this.SalidaService.updateSalida(this.salidaId, this.salidaForm).subscribe(
        salida => {
          this.messageBody = "La salida se ha editado correctamente"
          this.showSuccess();
          this.router.navigate(['main/almacen/salida']);
        },
        error => this.showError(error.error)
        );
    }
  }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  getSalida(id) {
    this.SalidaService.getSalida(id).pipe(first()).subscribe(
      salida => {
        this.loadSalida(salida);
      }
    )
  }

  getUsuarios(){
    this.UserService.getUsers().subscribe(
      (data: any)  => {
        this.dataSourceUsuarios = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.nombre + ' ' + val.apellido
            }
          }
        );
      },
      (error) => {
        console.error(error);
      }
    );
  }

  getRepuestos(){
    this.AlmacenService.getRepuestos().subscribe(
      (data: any)  => {
        this.dataSourceRepuestos = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.codigo_producto
            }
          }
        );
      },
      (error) => {
        console.error(error);
      }
    );
  }

  getSectores() {
    this.TipoService.getTipos('Sectores').subscribe(
      (data: any) => {
        this.dataSourceSectors = data.map(
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

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
