import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MessageService } from "../../../core/service/message.service";
import { AlmacenService } from "../../services/almacen.service";

@Component({
  selector: 'app-form-ajuste',
  templateUrl: './form-ajuste.component.html',
  styleUrls: ['./form-ajuste.component.scss']
})
export class FormAjusteComponent implements OnInit {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public repuestoId;
  public dataSourceRepuestos;
  public mode = 'add';
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "Ajuste creado correctamente";
  public form: FormGroup = new FormGroup({
    codigoProducto: new FormControl(''),
    modelo: new FormControl(''),
    nombre: new FormControl(''),
    stock: new FormControl(''),
    fecha_correccion: new FormControl(''),
  });

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  constructor(private MessageService: MessageService,
              private AlmacenService: AlmacenService) { }

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

  // loadEntrada(entrada) {
  //   this.mode = "edit";  
  //   this.form.controls.cantidad.setValue(entrada.cantidad);
  //   this.form.controls.fecha.setValue(entrada.fecha);
  //   this.form.controls.numeroOrdenCompra.setValue(entrada.numeroOrdenCompra);
  //   this.form.controls.precio.setValue(entrada.precio);
  //   this.form.controls.proveedor.setValue(entrada.proveedor);
  //   this.form.controls.repuesto_cod.setValue(entrada.repuesto.id);
  // }

  saveForm() {
    this.repuestoId;
    this.AlmacenService.addAjuste(this.repuestoId ,this.form).subscribe(
      data => {
        this.showSuccess();
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
  }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
