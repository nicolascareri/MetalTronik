import {Component, OnInit} from '@angular/core';
import {PlantaService} from '../../services/planta.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';
import { MessageService } from "../../../core/service/message.service";


@Component({
  selector: 'app-tabla-planta',
  templateUrl: './tabla-planta.component.html',
  styleUrls: ['./tabla-planta.component.scss']
})
export class TablaPlantaComponent implements OnInit {

  public plantaId: any;
  public mode = 'add';
  public section = 'Nueva planta';
  public buttonName = 'Crear planta';
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "Planta creada correctamente";
  public panelOpenState = false;
  public dataSourcePlants;
  public formPlanta = new FormGroup({
    nombre: new FormControl(''),
    estado: new FormControl(30)
  })




  constructor(private PlantaService: PlantaService,
              private router: Router,
              private MessageService: MessageService) {
  }


  ngOnInit(): void {

   this.getPlantas();

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

  clickedRow(row){
    this.router.navigate(['main/maquinas/formplanta/' + row.id]);
  }

  getPlantas(){
    this.PlantaService.getPlantas().subscribe(

      (data: any)  => { // Success
        this.dataSourcePlants = data;
      },
      (error) => {
        console.error(error);
      }

    );
  }

}
