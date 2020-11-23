import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ParteService } from "../../../services/parte.service";
import { MessageService } from "../../../../core/service/message.service";
import { Router } from '@angular/router';


@Component({
  selector: 'app-form-partes',
  templateUrl: './form-partes.component.html',
  styleUrls: ['./form-partes.component.scss']
})
export class FormPartesComponent implements OnInit {

  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "Añadido a selección";
  public section = 'Crear parte/s - 2/3';
  public buttonName = 'Siguiente';
  public partsForm: FormGroup = new FormGroup({
    codigo: new FormControl(''),
    nombre: new FormControl(''),
  });
  public parts: any = [];

  constructor(private ParteService: ParteService,
              private MessageService: MessageService,
              private router: Router) { }

  ngOnInit(): void {
  }

  add(){
    this.ParteService.postParte(this.partsForm).subscribe(
      parte => {
        this.parts.push(parte);
        this.ParteService.setLastInsert(parte);
        this.messageBody = "Añadido a selección";
        this.showSuccess();
      },
      error => this.showError(error.error)
    );
  }

  delete(part){
    var index = this.parts.indexOf(part);
    if (index > -1) {
      this.parts.splice(index, 1);
      this.deletePart(part);
      this.ParteService.deleteLastInsert(part);
    }
  }

  deletePart(part){
    this.ParteService.deleteParte(part.id).subscribe(
      parte => {
        this.messageBody = "Eliminado de la selección"
        this.showSuccess();
      },
      error => this.showError(error.error)
    );
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

  next(){
    this.router.navigate(['main/maquinas/form-partes-asoc']);
  }

}
