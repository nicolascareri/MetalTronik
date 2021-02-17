import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MessageService } from "../../../core/service/message.service";
import { AlmacenService } from "../../services/almacen.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-ajuste',
  templateUrl: './form-ajuste.component.html',
  styleUrls: ['./form-ajuste.component.scss']
})
export class FormAjusteComponent implements OnInit {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public repuestoId;
  public dataSourceRepuestos;
  public diferenciaStock: any;
  public mode = 'add';
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "Ajuste creado correctamente";
  public form: FormGroup = new FormGroup({
    codigo_producto: new FormControl(''),
    modelo: new FormControl(''),
    nombre: new FormControl(''),
    marca: new FormControl(''),
    stock: new FormControl(''),
    stock_ajustado: new FormControl(''),
    fecha_correccion: new FormControl(''),
  });

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  constructor(private MessageService: MessageService,
              private AlmacenService: AlmacenService,
              private router: Router) { }

  ngOnInit(): void {
    this.getRepuestos();
  }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  saveForm() {
    let request: any = {
      'codigo_producto': this.form.controls.codigo_producto.value,
      'fecha_correccion': this.form.controls.fecha_correccion.value,
      'marca': this.form.controls.marca.value,
      'modelo': this.form.controls.modelo.value,
      'nombre': this.form.controls.nombre.value,
      'stock': this.form.controls.stock.value,
      'stock_ajustado': this.form.controls.stock.value - this.diferenciaStock,
      }
    this.repuestoId;
    this.AlmacenService.addAjuste(this.repuestoId, request).subscribe(
      data => {
        this.showSuccess();
        this.router.navigate(['main/almacen/ajuste']);
      },
      error => this.showError(error.error)
    );
  }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  getRepuestos(){
    this.AlmacenService.getRepuestos().subscribe(
      (data: any) => {
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
        console.log(error.error);
      }
    );
  }

  getRepuestoId(id){
    this.repuestoId = id.split(" ");
    id = this.repuestoId[1];
    this.repuestoId = id;
    this.getRepuesto(this.repuestoId);
  }

  getRepuesto(id){
    this.AlmacenService.getRepuesto(id).subscribe(
      (data: any) => {
        this.diferenciaStock = data.existencia;
      },
      (error) => {
        console.log(error.error);
      }
    );
  }
  

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
