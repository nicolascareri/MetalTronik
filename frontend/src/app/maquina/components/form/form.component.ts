import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MaquinaService } from '../../services/maquina.service';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { MessageService } from "../../../core/service/message.service";
import { ParteService } from "../../services/parte.service";
import { TipoService } from '../../../tipo/services/tipo.service';

@Component({
  selector: 'app-form-maquina',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormMaquinaComponent implements OnInit {

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public dataSourcePlants: any;
  public dataSourceSectors: any;
  public maquinaId: any;
  public routeButton = "../";
  public mode = 'add';
  public section = 'Nueva máquina - 1/3';
  public buttonName = 'Siguiente';
  public path = 'form-partes'
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "La máquina se ha creado correctamente";
  public machinesForm: FormGroup = new FormGroup({
    maquina_cod: new FormControl(''),
    equipo: new FormControl(''),
    descripcion: new FormControl(''),
    planta_id: new FormControl(''),
    sector_id: new FormControl(''),
    estado: new FormControl(30)
  });
  public partsForm: FormGroup = new FormGroup({
    codigo: new FormControl(''),
    nombre: new FormControl(''),
  });
  public parts: any = [];
  partForDelete: any = [];

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  constructor(private MaquinaService: MaquinaService,
    private TipoService: TipoService,
    private router: Router,
    private route: ActivatedRoute,
    private MessageService: MessageService,
    private ParteService: ParteService) {

  }

  ngOnInit(): void {
    this.maquinaId = this.route.snapshot.params.id;
    this.getPlantas();
    this.getSectores();
  }

  ngAfterViewInit(): void {
    if (this.maquinaId) {
      this.getMaquina(this.maquinaId);
      this.getParts(this.maquinaId);
    }
  }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  loadMaquina(maquina) {
    this.mode = "edit";
    this.routeButton = "../../";
    this.section = 'Editar máquina';
    this.buttonName = 'Confirmar cambios';
    this.machinesForm.controls.maquina_cod.setValue(maquina.maquina_cod);
    this.machinesForm.controls.equipo.setValue(maquina.equipo);
    this.machinesForm.controls.descripcion.setValue(maquina.descripcion);
    this.machinesForm.controls.planta_id.setValue(maquina.planta.id);
    this.machinesForm.controls.sector_id.setValue(maquina.sector.id);
  }

  linkParts(){
    this.parts = this.parts.map(
      val => {
        return val.id;
      }
    );
    this.ParteService.linkPart(this.maquinaId, this.parts).subscribe(
      parte => {
      },
      error => this.showError(error.error)
    );
  }

  saveForm() {
    if (this.mode === 'add') {
      this.MaquinaService.postMaquina(this.machinesForm).subscribe(
        maquina => {
          this.MaquinaService.setLastInsert(maquina);
          this.router.navigate(['main/maquinas/form-partes']);
        },
        error => this.showError(error.error)
      );
    } else {

      this.MaquinaService.updateMaquina(this.maquinaId, this.machinesForm).subscribe(
        maquina => {
          this.partForDelete.forEach(parte => {
            this.deletePart(parte);
          });
          this.linkParts();
          this.messageBody = "La máquina se ha editado correctamente"
          this.showSuccess();
          this.router.navigate(['main/maquinas']);
        },
        error => this.showError(error.error)
        );
    }
  }

  addParte(){
    this.ParteService.postParte(this.partsForm).subscribe(
      parte => {
        this.parts.push(parte);
        this.messageBody = "Añadido a selección";
        this.showSuccess();
      },
      error => this.showError(error.error)
    );
  }

  delete(part){
    var index = this.parts.indexOf(part);
    if (index > -1) {
      this.partForDelete.push(part);
      this.parts.splice(index, 1);
      this.messageBody = "Eliminado de la selección"
      this.showSuccess();
    }
  }

  deletePart(part){
    this.ParteService.deleteParte(part.id).subscribe(
      parte => {},
      error => this.showError(error.error)
    );
  }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  getMaquina(id) {
    this.MaquinaService.getMaquina(id).pipe(first()).subscribe(
      maquina => {
        this.loadMaquina(maquina);
      }
    )
  }

  getPlantas() {
    this.TipoService.getTipos('Plantas').subscribe(
      (data: any) => {
        this.dataSourcePlants = data.map(
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
  
  getParts(id){
    this.ParteService.getByMaquina(id).subscribe(
      (data: any) => {
        this.parts = data.map(
          val => {
            return {
              "id": val.id,
              "nombre": val.nombre,
              "codigo": val.codigo
            }
          }
        );
      },
      (error) => {
        console.error(error);
      }
    );
  }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




}
