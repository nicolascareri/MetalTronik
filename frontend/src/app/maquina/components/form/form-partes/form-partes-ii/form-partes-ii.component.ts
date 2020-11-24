import { Component, OnInit } from '@angular/core';
import { ParteService } from "../../../../services/parte.service";
import { MessageService } from "../../../../../core/service/message.service";
import { MaquinaService } from "../../../../services/maquina.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-form-partes-ii',
  templateUrl: './form-partes-ii.component.html',
  styleUrls: ['./form-partes-ii.component.scss']
})
export class FormPartesIIComponent implements OnInit {

  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "Maquina creada correctamente";
  public section = 'Confirmación - 3/3';
  public buttonName = 'Confirmar';
  public parts: any = [];
  public maquina : any;

  constructor(private ParteService: ParteService,
              private MessageService: MessageService,
              private MaquinaService: MaquinaService,
              private router: Router) { }

  ngOnInit(): void {
    this.getSelecction();
  }

  getSelecction(){
    this.parts = this.ParteService.getLastInsert();
    this.maquina = this.MaquinaService.getLastInsert();
  }

  finalize(){
    this.parts = this.parts.map(
      val => {
        return val.id;
      }
    );
    this.ParteService.linkPart(this.maquina.id, this.parts).subscribe(
      parte => {

        this.showSuccess();
      },
      error => this.showError(error.error)
    );
    this.router.navigate(['main/maquinas']);
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
