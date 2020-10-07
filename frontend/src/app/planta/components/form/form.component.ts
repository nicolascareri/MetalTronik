import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { PlantaService } from '../../services/planta.service';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { MessageService } from "../../../core/service/message.service";

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormPlantaComponent implements OnInit {

  public formPlanta: FormGroup;
  public plantaId: any;
  public mode = 'add';
  public section = 'Nueva planta';
  public buttonName = 'Crear planta';
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "Planta creada correctamente";

  constructor(private PlantaService: PlantaService,
    private router: Router,
    private route: ActivatedRoute,
    private MessageService: MessageService) {
    this.formPlanta = this.createFormGroup();
  }

  ngOnInit(): void {
    this.plantaId = this.route.snapshot.params.id;
  }

  ngAfterViewInit(): void {
    if (this.plantaId) {
      this.getPlanta(this.plantaId);
    }
  }

  showSuccess() {
    this.MessageService.showSuccess({
      title: this.messageTitleSuccess,
      body: this.messageBody
    });
  }

  showError(message) {
    this.MessageService.showError({
      title: this.messageTitleError,
      body: message.errors ? message.errors[0].defaultMessage + ". campo: " + message.errors[0].field + ", Valor rechazado: " + message.errors[0].rejectedValue : message.error + " Campo " + message.campo
    })
  }

  getPlanta(id) {
    this.PlantaService.getPlanta(id).pipe(first()).subscribe(
      planta => {
        this.loadPlanta(planta);
      }
    )
  }

  loadPlanta(planta) {
    this.mode = "edit";
    this.section = 'Editar planta';
    this.buttonName = 'Confirmar cambios';  
    console.log(planta);
    this.formPlanta.controls.nombre.setValue(planta.nombre);
  }

  createFormGroup() {
    return new FormGroup({
      nombre: new FormControl(''),
      estado: new FormControl(30)
    })
  }


  resetForm() {
    this.formPlanta.reset();
  }

  saveForm() {
    if (this.mode === 'add') {
      this.PlantaService.postPlanta(this.formPlanta).subscribe(
        planta => {
          this.showSuccess();
        },
        error => this.showError(error.error)
      );
    } else {
      this.PlantaService.updatePlanta(this.plantaId, this.formPlanta).subscribe(
        planta => {
          this.messageBody = "La planta se edito correctamente"
          this.showSuccess();
        },
        error => this.showError(error.error)
      );
    }
  }


}
