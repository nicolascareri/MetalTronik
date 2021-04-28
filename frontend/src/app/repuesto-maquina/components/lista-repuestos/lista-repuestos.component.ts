import { Component, OnInit } from '@angular/core';
import { RepuestoMaquinaService } from "../../services/repuesto-maquina.service";
import { MaquinaService } from "../../../maquina/services/maquina.service";
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { ParteService } from "../../../maquina/services/parte.service";
import { MessageService } from "../../../core/service/message.service";
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-lista-repuestos',
  templateUrl: './lista-repuestos.component.html',
  styleUrls: ['./lista-repuestos.component.scss']
})

export class ListaRepuestosComponent implements OnInit {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  public routeButton = "../";
  public dataSourceRepuestos: any = [];
  public dataSourceMaquinas: any;
  public dataSourcePartes: any;
  public repuestos: any = [];
  public modelos: any = [];
  public modeloValue: any;
  public observaciones: '';
  public seleccion: any = [];
  public maquinaId: any;
  public maquinaPart: any;
  public partId: any;
  public cntInstalada: any;
  public idSeleccion = 0;
  public repuestosFilter: any;
  public repuesto_id: any;
  // public maquina: FormGroup = new FormGroup({
  //   maquina_id: new FormControl(''),
  //   parte_id: new FormControl(''),
  // });
  public form: FormGroup;
  // public requestList: any = [];
  public asocId: any;
  public mode = 'add';
  public section = 'Nueva asociacion';
  public buttonName = 'Aceptar';


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  constructor(private RepuestoMaquinaService: RepuestoMaquinaService,
              private MaquinaService: MaquinaService,
              private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private ParteService: ParteService,
              private MessageService: MessageService,
              private router: Router,) 
  {}

  ngOnInit(): void {
    this.asocId = this.route.snapshot.params.id;
    this.getRepuestos();
    this.getMaquinas();

    this.form = this.formBuilder.group({
      maquina_id: [''],
      parte_id: [''],
      repuesto_id: [''],
      cantidad_instalada: [''],
      observaciones: ''
    });

  }

  ngAfterViewInit(): void {
    if (this.asocId) {
      this.getAsoc(this.asocId);
    }
  }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "Asociación creada correctamente";

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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  asociar() {
    let request = {
      'maquina_id': this.maquinaPart,
      'parte_id' : this.partId,
      'repuesto_id': this.form.controls.repuesto_id.value,
      'observaciones': this.form.controls.observaciones.value,
      'cantidad_instalada': this.form.controls.cantidad_instalada.value
    }
    if (this.mode === 'add') {
      this.RepuestoMaquinaService.asociarRepuestos(request).subscribe(
        repuestos => {
          this.messageBody = "Asociación creada correctamente";
          this.showSuccess();
          this.router.navigate(['main/repuestos']);
      },
      (error) => {
        this.messageBody = "ERROR"
        this.showError(this.messageBody);
      });
    } else {
      this.RepuestoMaquinaService.updateAsociacion(this.asocId, request).subscribe(
        asoc => {
          this.messageBody = "La asociacion se ha editado correctamente"
          this.showSuccess();
          this.router.navigate(['main/repuestos']);
        },
        error => this.showError(error.error)
        );
    }
  }
  
  loadAsoc(asoc) {
    this.mode = "edit";
    this.routeButton = "../../";
    this.section = 'Editar asociacion';
    this.buttonName = 'Confirmar cambios';
    this.form.controls.maquina_id.setValue(asoc.maquina_id);
    this.form.controls.parte_id.setValue(asoc.parte.id);
    this.form.controls.repuesto_id.setValue(asoc.repuesto_id);
    this.form.controls.cantidad_instalada.setValue(asoc.cantidad_instalada);
    this.form.controls.observaciones.setValue(asoc.observaciones);
  }
  // agruopData() {
  //   const ctrl = this.form.controls;
  //   const repuesto = {
  //     "cantidad_instalada": ctrl.cantidad_instalada.value,
  //     "repuesto_id": ctrl.repuesto_id.value
  //   };
  
  //   this.repuestos.push(repuesto);

  //   const seleccion = {
  //     "cantidad_instalada": ctrl.cantidad_instalada.value,
  //     "repuesto_id": ctrl.repuesto_id.value,
  //   }
  //   this.seleccion.push(seleccion);
  //   this.requestList.push(this.repuestos);
  // }


  // deleteSelection() {
  //   this.seleccion.pop();
  // }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  getRepuestos(){
    this.RepuestoMaquinaService.getRepuestos().subscribe(
      (data: any) => {
        this.dataSourceRepuestos = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.codigo_producto + " - " + val.nombre
            }
          }
        );
        data.forEach(repuesto => {
          if (!this.modelos.includes(repuesto.modelo)) {
            this.modelos.push(repuesto.modelo)
          }
        });
      },
      (error) => {
        console.error(error);
      }
    );
  }

  getMaquinas(){
    this.MaquinaService.getMaquinas().subscribe(
      (data: any) => {
        this.dataSourceMaquinas = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.maquina_cod + " - " + val.equipo
            }
          }
        );
      },
      (error) => {
        console.log(error.error);
        
      }
    );
  }


  getParts(id){
    this.ParteService.getByMaquina(id).subscribe(
      (data: any) => {
        this.dataSourcePartes = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.nombre + " - " + val.codigo
            }
          }
        );
      },
      (error) => {
        console.error(error);
      }
    );
  }

  getAsoc(id){
    this.RepuestoMaquinaService.getAsocById(id).pipe(first()).subscribe(
      asoc => {
        this.loadAsoc(asoc);
      }
    )
  }

  getMachineId(id){
    this.maquinaPart = id.split(" ");
    id = this.maquinaPart[1];
    this.getParts(id);
    this.maquinaPart = id;
  }

  getPartId(id){
    this.partId = id.split(" ");
    id = this.partId[1];
    this.partId = id;
  }

  getRepuestId(id){
    this.repuesto_id = id.split(" ");
    id = this.repuesto_id[1];
    this.repuesto_id = id;
  }

  getObservaciones(ob){
    this.observaciones = ob;
    console.log(this.observaciones);
    
  }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
    
    
  }







