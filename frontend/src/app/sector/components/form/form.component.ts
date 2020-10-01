import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { SectorService } from '../../services/sector.service';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { MessageService } from "../../../core/service/message.service";

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormSectorComponent implements OnInit {

  public form: FormGroup;
  public sectorId: any;
  public mode = 'add';
  public section = 'Nuevo sector';
  public buttonName = 'Crear sector';
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "Sector creado correctamente";

  constructor(private SectorService: SectorService,
    private router: Router,
    private route: ActivatedRoute,
    private MessageService: MessageService) {
    this.form = this.createFormGroup();
  }

  ngOnInit(): void {
    this.sectorId = this.route.snapshot.params.id;
  }

  ngAfterViewInit(): void {
    if (this.sectorId) {
      this.getSector(this.sectorId);
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
      body: message.errors ? message.errors[0].defaultMessage + ". campo: " + message.errors[0].field + ", Valor rechazado: " + message.errors[0].rejectedValue : message.error
    })
  }

  getSector(id) {
    this.SectorService.getSector(id).pipe(first()).subscribe(
      sector => {
        this.loadSector(sector);
      }
    )
  }

  loadSector(sector) {
    this.mode = "edit";
    this.section = 'Editar sector';
    this.buttonName = 'Confirmar cambios';
    console.log(sector);
    this.form.controls.descripcion.setValue(sector.descripcion);
  }

  createFormGroup() {
    return new FormGroup({
      descripcion: new FormControl(''),
      estado: new FormControl(30)
    })
  }

  resetForm() {
    this.form.reset();
  }

  saveForm() {
    if (this.mode === 'add') {
      this.SectorService.postSector(this.form).subscribe(
        sector => {
          this.showSuccess();
        },
        error => this.showError(error.error)
      );
    } else {
      this.SectorService.updateSector(this.sectorId, this.form).subscribe(
        sector => {
          this.messageBody = "El sector se edito correctamente"
          this.showSuccess();
        },
        error => this.showError(error.error)
      );
    }
  }

}
