import {Component, OnInit} from '@angular/core';
import {SectorService} from '../../services/sector.service';
import { Router } from '@angular/router';
import { FormControl, FormGroup } from '@angular/forms';
import { MessageService } from "../../../core/service/message.service";

@Component({
  selector: 'app-tabla-sector',
  templateUrl: './tabla-sector.component.html',
  styleUrls: ['./tabla-sector.component.scss']
})
export class TablaSectorComponent implements OnInit {

  public form = new FormGroup({
    descripcion: new FormControl(''),
    estado: new FormControl(30)
  });
  public panelOpenState = false;
  public dataSourceSectors;
  public sectorId: any;
  public mode = 'add';
  public section = 'Nuevo sector';
  public buttonName = 'Crear sector';
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "Sector creado correctamente";

  constructor(private SectorService: SectorService,
              private MessageService: MessageService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getSectores();
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

  clickedRow(row){
    this.router.navigate(['main/maquinas/formsector/' + row.id]);
  }

  getSectores(){
    this.SectorService.getSectores().subscribe(
      (data: any) => {
        this.dataSourceSectors = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
