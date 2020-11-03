import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ParteService } from "../../../services/parte.service";
import { MessageService } from "../../../../core/service/message.service";

@Component({
  selector: 'app-form-partes',
  templateUrl: './form-partes.component.html',
  styleUrls: ['./form-partes.component.scss']
})
export class FormPartesComponent implements OnInit {

  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "La maquina se ha creado correctamente";
  public section = 'Crear parte/s';
  public buttonName = 'Siguiente';
  public partsForm: FormGroup = new FormGroup({
    codigo: new FormControl(''),
    nombre: new FormControl(''),
  });
  public partsCreated = [];

  constructor(private ParteService: ParteService,
              private MessageService: MessageService) { }

  ngOnInit(): void {
  }

  saveForm(){
    this.ParteService.postParte(this.partsForm).subscribe(
      parte => {
        this.showSuccess();
        this.partsCreated.push(parte);
        console.log(this.partsCreated);
        
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

}
