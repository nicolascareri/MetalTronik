import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { MessageService } from "../../../core/service/message.service";
import { SalidaService } from "../../services/salida.service";
import { AlmacenService } from "../../services/almacen.service";
import { SectorService } from "../../../sector/services/sector.service";
import { UserService } from "../../../usuarios/services/user.service";

@Component({
  selector: 'app-form-salida',
  templateUrl: './form-salida.component.html',
  styleUrls: ['./form-salida.component.scss']
})
export class FormSalidaComponent implements OnInit {

  
  public dataSourceSalida;
  public dataSourceRepuestos;
  public dataSourceUsuarios;
  public dataSourceSectores;
  public salidaId: any;
  public mode = 'add';
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "La salida se ha creado correctamente";
  public salidaForm: FormGroup = new FormGroup({
    cantidad: new FormControl(''),
    fecha: new FormControl(''),
    repuesto_cod: new FormControl(''),
    sector_cod: new FormControl(''),
    solicitante_cod: new FormControl(''),
  });

  constructor(private SalidaService: SalidaService,
              private route: ActivatedRoute,
              private MessageService: MessageService,
              private AlmacenService: AlmacenService,
              private SectorService: SectorService,
              private UserService: UserService) { }

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

  getSalida(id) {
    this.SalidaService.getSalida(id).pipe(first()).subscribe(
      salida => {
        this.loadSalida(salida);
      }
    )
  }

  getSectores(){
    this.SectorService.getSectores().subscribe(
      (data: any)  => {
        this.dataSourceSectores = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.descripcion
            }
          }
        );
      },
      (error) => {
        console.error(error);
      }
    );
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
              "descripcion": val.codigoProducto
            }
          }
        );
      },
      (error) => {
        console.error(error);
      }
    );
  }

  loadSalida(salida) {
    this.mode = "edit";
    console.log(salida);
    this.salidaForm.controls.cantidad.setValue(salida.cantidad);
    this.salidaForm.controls.fecha.setValue(salida.fecha);
    this.salidaForm.controls.repuesto_cod.setValue(salida.repuesto_cod);
    this.salidaForm.controls.sector_cod.setValue(salida.sector_cod);
    this.salidaForm.controls.solicitante_cod.setValue(salida.solicitante_cod);
  }

  resetForm() {
    this.salidaForm.reset();
  }

  saveForm() {
    if (this.mode === 'add') {
      this.SalidaService.postSalida(this.salidaForm).subscribe(
        salida => {
          this.showSuccess();
        },
        error => this.showError(error.error)
      );
    } else {
      console.log(this.salidaForm);
      this.SalidaService.updateSalida(this.salidaId, this.salidaForm).subscribe(
        salida => {
          this.messageBody = "La salida se ha editado correctamente"
          this.showSuccess();
        },
        error => this.showError(error.error)
        );
    }
  }

}
