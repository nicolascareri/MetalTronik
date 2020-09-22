import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { MessageService } from "../../../core/service/message.service";
import { EntradaService } from "../../services/entrada.service";
import { AlmacenService } from "../../services/almacen.service";

@Component({
  selector: 'app-form-entrada',
  templateUrl: './form-entrada.component.html',
  styleUrls: ['./form-entrada.component.scss']
})
export class FormEntradaComponent implements OnInit {

  public dataSourceRepuestos;
  public entradaId: any;
  public mode = 'add';
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "La entrada se ha creado correctamente";
  public entradaForm: FormGroup = new FormGroup({
    cantidad: new FormControl(''),
    fecha: new FormControl(''),
    numeroOrdenCompra: new FormControl(''),
    precio: new FormControl(''),
    proveedor: new FormControl(''),
    repuesto_cod: new FormControl('')
  });

  constructor(private EntradaService: EntradaService,
              private route: ActivatedRoute,
              private MessageService: MessageService,
              private AlmacenService: AlmacenService) 
              { }

  ngOnInit(): void {
    this.entradaId = this.route.snapshot.params.id;
    this.getRepuestos();
  }

  ngAfterViewInit(): void {
    if (this.entradaId) {
      this.getEntrada(this.entradaId);
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

  getEntrada(id) {
    this.EntradaService.getEntrada(id).pipe(first()).subscribe(
      entrada => {
        this.loadEntrada(entrada);
      }
    )
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

  loadEntrada(entrada) {
    this.mode = "edit";
    console.log(entrada);
    this.entradaForm.controls.cantidad.setValue(entrada.cantidad);
    this.entradaForm.controls.fecha.setValue(entrada.fecha);
    this.entradaForm.controls.numeroOrdenCompra.setValue(entrada.numeroOrdenCompra);
    this.entradaForm.controls.precio.setValue(entrada.precio);
    this.entradaForm.controls.proveedor.setValue(entrada.proveedor);
    this.entradaForm.controls.repuesto_cod.setValue(entrada.repuesto.id);
  }

  resetForm() {
    this.entradaForm.reset();
  }

  saveForm() {
    if (this.mode === 'add') {
      this.EntradaService.postEntrada(this.entradaForm).subscribe(
        entrada => {
          this.showSuccess();
        },
        error => this.showError(error.error)
      );
    } else {
      console.log(this.entradaForm);
      this.EntradaService.updateEntrada(this.entradaId, this.entradaForm).subscribe(
        entrada => {
          this.messageBody = "La entrada se ha editado correctamente"
          this.showSuccess();
        },
        error => this.showError(error.error)
        );
    }
  }


}
