import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MaquinaService } from '../../services/maquina.service';
import { PlantaService } from '../../../planta/services/planta.service';
import { SectorService } from '../../../sector/services/sector.service';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { MessageService } from "../../../core/service/message.service";

@Component({
  selector: 'app-form-maquina',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormMaquinaComponent implements OnInit {


  public dataSourcePlants: any;
  public dataSourceSectors: any;
  public maquinaId: any;
  public mode = 'add';
  public section = 'Nueva maquina';
  public buttonName = 'Crear maquina';
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "La maquina se ha creado correctamente";
  public machinesForm: FormGroup = new FormGroup({
    maquina_cod: new FormControl(''),
    nro_serie: new FormControl(''),
    modelo: new FormControl(''),
    equipo: new FormControl(''),
    datos_tecnicos: new FormControl(''),
    descripcion: new FormControl(''),
    planta_cod: new FormControl(''),
    sector_cod: new FormControl(''),
    estado: new FormControl(30)
  });

  constructor(private MaquinaService: MaquinaService,
    private PlantaService: PlantaService,
    private SectorService: SectorService,
    private router: Router,
    private route: ActivatedRoute,
    private MessageService: MessageService) {

  }

  ngOnInit(): void {
    this.maquinaId = this.route.snapshot.params.id;
    this.getPlantas();
    this.getSectores();
  }

  ngAfterViewInit(): void {
    if (this.maquinaId) {
      this.getMaquina(this.maquinaId);
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

  getMaquina(id) {
    this.MaquinaService.getMaquina(id).pipe(first()).subscribe(
      maquina => {
        this.loadMaquina(maquina);
      }
    )
  }

  loadMaquina(maquina) {
    this.mode = "edit";
    this.section = 'Editar maquina';
    this.buttonName = 'Confirmar cambios';
    this.machinesForm.controls.maquina_cod.setValue(maquina.maquina_cod);
    this.machinesForm.controls.nro_serie.setValue(maquina.nro_serie);
    this.machinesForm.controls.modelo.setValue(maquina.modelo);
    this.machinesForm.controls.equipo.setValue(maquina.equipo);
    this.machinesForm.controls.datos_tecnicos.setValue(maquina.datos_tecnicos);
    this.machinesForm.controls.descripcion.setValue(maquina.descripcion);
    this.machinesForm.controls.planta_cod.setValue(maquina.planta.id);
    this.machinesForm.controls.sector_cod.setValue(maquina.sector.id);
  }

  getPlantas() {
    this.PlantaService.getPlantas().subscribe(
      (data: any) => {
        this.dataSourcePlants = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.nombre
            }
          }
        );
        console.log(this.dataSourcePlants);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  getSectores() {
    this.SectorService.getSectores().subscribe(
      (data: any) => {
        this.dataSourceSectors = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.descripcion
            }
          }
        );
        console.log(this.dataSourceSectors);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  resetForm() {
    this.machinesForm.reset();
  }

  saveForm() {
    if (this.mode === 'add') {
      this.MaquinaService.postMaquina(this.machinesForm).subscribe(
        maquina => {
          this.showSuccess();
        },
        error => this.showError(error.error)
      );
    } else {
      console.log(this.machinesForm);
      this.MaquinaService.updateMaquina(this.maquinaId, this.machinesForm).subscribe(
        maquina => {
          this.messageBody = "La maquina se ha editado correctamente"
          this.showSuccess();
        },
        error => this.showError(error.error)
        );
    }
  }

}
