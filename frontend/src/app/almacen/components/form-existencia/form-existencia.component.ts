import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { MessageService } from "../../../core/service/message.service";
import { AlmacenService } from "../../services/almacen.service";
import { UNIDADES } from "../../../core/constants/constants";

@Component({
  selector: 'app-form-existencia',
  templateUrl: './form-existencia.component.html',
  styleUrls: ['./form-existencia.component.scss']
})
export class FormExistenciaComponent implements OnInit {

  public repuestoId: any;
  public mode = 'add';
  public section = 'Nuevo repuesto';
  public buttonName = 'Crear repuesto';
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "El repuesto se ha creado correctamente";
  public unidades: any;
  public repuestForm: FormGroup = new FormGroup({
    codigoProducto: new FormControl(''),
    nombre: new FormControl(''),
    marca: new FormControl(''),
    modelo: new FormControl(''),
    tipoRepuesto: new FormControl(''),
    precio: new FormControl(''),
    unidad: new FormControl(''),
    ubicacion: new FormControl(''),
    puntoPedido: new FormControl(''),
    stockObjetivo: new FormControl('')
  });

  constructor( private router: Router,
               private route: ActivatedRoute,
               private MessageService: MessageService,
               private AlmacenService: AlmacenService) { }

  ngOnInit(): void {
    this.repuestoId = this.route.snapshot.params.id;
    this.unidades = UNIDADES;
  }

  ngAfterViewInit(): void {
    if (this.repuestoId) {
      this.getRepuesto(this.repuestoId);
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

  getRepuesto(id) {
    this.AlmacenService.getRepuesto(id).pipe(first()).subscribe(
      repuesto => {
        this.loadRepuesto(repuesto);
      }
    )
  }

  loadRepuesto(repuesto) {
    this.mode = "edit";
    this.section = 'Editar repuesto';
    this.buttonName = 'Confirmar cambios';
    console.log(repuesto);
    this.repuestForm.controls.codigoProducto.setValue(repuesto.codigoProducto);
    this.repuestForm.controls.nombre.setValue(repuesto.nombre);
    this.repuestForm.controls.marca.setValue(repuesto.marca);
    this.repuestForm.controls.modelo.setValue(repuesto.modelo);
    this.repuestForm.controls.tipoRepuesto.setValue(repuesto.tipoRepuesto);
    this.repuestForm.controls.precio.setValue(repuesto.precio);
    this.repuestForm.controls.unidad.setValue(repuesto.unidad);
    this.repuestForm.controls.ubicacion.setValue(repuesto.ubicacion);
    this.repuestForm.controls.puntoPedido.setValue(repuesto.puntoPedido);
    this.repuestForm.controls.stockObjetivo.setValue(repuesto.stockObjetivo);
  }

  resetForm() {
    this.repuestForm.reset();
  }

  saveForm() {
    if (this.mode === 'add') {
      this.AlmacenService.postRepuesto(this.repuestForm).subscribe(
        repuesto => {
          this.showSuccess();
        },
        error => this.showError(error.error)
      );
    } else {
      console.log(this.repuestForm);
      this.AlmacenService.updateRepuesto(this.repuestoId, this.repuestForm).subscribe(
        repuesto => {
          this.messageBody = "El repuesto se ha editado correctamente"
          this.showSuccess();
        },
        error => this.showError(error.error)
        );
    }
  }



}
