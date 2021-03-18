import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { MessageService } from "../../../core/service/message.service";
import { AlmacenService } from "../../services/almacen.service";
import { UNIDADES } from "../../../core/constants/constants";
import { TipoService } from '../../../tipo/services/tipo.service';

@Component({
  selector: 'app-form-existencia',
  templateUrl: './form-existencia.component.html',
  styleUrls: ['./form-existencia.component.scss']
})

export class FormExistenciaComponent implements OnInit {

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public dataSourceTipos;
  public repuestoId: any;
  public mode = 'add';
  public routeButton = "../";
  public section = 'Nuevo repuesto';
  public buttonName = 'Crear repuesto';
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "El repuesto se ha creado correctamente";
  public unidades: any;
  public repuestForm: FormGroup = new FormGroup({
    codigo_producto: new FormControl(''),
    nombre: new FormControl(''),
    marca: new FormControl(''),
    modelo: new FormControl(''),
    tipoRepuesto_id: new FormControl(''),
    unidad: new FormControl(''),
    ubicacion: new FormControl(''),
    minimo: new FormControl(''),
    objetivo: new FormControl('')
  });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  constructor( private router: Router,
               private route: ActivatedRoute,
               private MessageService: MessageService,
               private AlmacenService: AlmacenService,
               private TipoService: TipoService) { }

  ngOnInit(): void {
    this.getTipos();
    this.repuestoId = this.route.snapshot.params.id;
    this.unidades = UNIDADES;
  }

  ngAfterViewInit(): void {
    if (this.repuestoId) {
      this.getRepuesto(this.repuestoId);
    }
  }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  loadRepuesto(repuesto) {
    this.mode = "edit";
    this.routeButton = "../../";
    this.section = 'Editar repuesto';
    this.buttonName = 'Confirmar cambios';
    this.repuestForm.controls.codigo_producto.setValue(repuesto.codigo_producto);
    this.repuestForm.controls.nombre.setValue(repuesto.nombre);
    this.repuestForm.controls.marca.setValue(repuesto.marca);
    this.repuestForm.controls.modelo.setValue(repuesto.modelo);
    this.repuestForm.controls.tipoRepuesto_id.setValue(repuesto.tipo_repuesto.id);
    this.repuestForm.controls.unidad.setValue(repuesto.unidad);
    this.repuestForm.controls.ubicacion.setValue(repuesto.ubicacion);
    this.repuestForm.controls.minimo.setValue(repuesto.stock.minimo);
    this.repuestForm.controls.objetivo.setValue(repuesto.stock.objetivo);
  }

  saveForm() {
    let request = {
      codigo_producto : this.repuestForm.controls.codigo_producto.value,
      marca : this.repuestForm.controls.marca.value,
      modelo: this.repuestForm.controls.modelo.value,
      nombre: this.repuestForm.controls.nombre.value,
      stock : {
        actual: 0,
        minimo: this.repuestForm.controls.minimo.value,
        objetivo: this.repuestForm.controls.objetivo.value
      },
      tipoRepuesto_id : this.repuestForm.controls.tipoRepuesto_id.value,
      ubicacion: this.repuestForm.controls.ubicacion.value,
      unidad: this.repuestForm.controls.unidad.value
    }   
    if (this.mode === 'add') {
   
      this.AlmacenService.postRepuesto(request).subscribe(
        repuesto => {
          this.showSuccess();
          this.router.navigate(['main/almacen/existencia']);
        },
        error => this.showError(error.error)
      );
    } else { 
      this.AlmacenService.updateRepuesto(this.repuestoId, request).subscribe(
        repuesto => {
          this.messageBody = "El repuesto se ha editado correctamente"
          this.showSuccess();
          this.router.navigate(['main/almacen/existencia']);
        },
        error => this.showError(error.error)
        );
    }
  }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  getRepuesto(id) {
    this.AlmacenService.getRepuesto(id).pipe(first()).subscribe(
      repuesto => {
        this.loadRepuesto(repuesto);
      }
    )
  }

  getTipos() {
    this.TipoService.getTipos('Repuestos').subscribe(
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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  

}
