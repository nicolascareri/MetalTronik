import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { MessageService } from "../../../../core/service/message.service";
import { TareaService } from "../../../services/tarea.service";
import { MaquinaService } from "../../../../maquina/services/maquina.service";
import { ParteService } from "../../../../maquina/services/parte.service";

@Component({
  selector: 'app-form-planificacion',
  templateUrl: './form-planificacion.component.html',
  styleUrls: ['./form-planificacion.component.scss']
})
export class FormPlanificacionComponent implements OnInit {

  public dataSourceMaquinas;
  public dataSourcePartes;
  public dataSourceHistorial;
  public tareaId: any;
  public maquinaId: any;
  public mode = 'add';
  public section = 'Programar tarea';
  public buttonName = 'Crear tarea'
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageBody: any = "La tarea se ha programado correctamente";
  public tareaForm: FormGroup = new FormGroup({
    estado: new FormControl(30),
    frecuencia: new FormControl(''),
    inicio: new FormControl(''),
    maquina_cod: new FormControl(''),
    parte_cod: new FormControl(''),
    tarea: new FormControl('')
  });

  constructor(private TareaService: TareaService,
              private MaquinaService: MaquinaService,
              private route: ActivatedRoute,
              private router: Router,
              private MessageService: MessageService,
              private ParteService: ParteService) { }
              

  ngOnInit(): void {
    this.tareaId = this.route.snapshot.params.id;
    this.getMaquinas();
  }

  ngAfterViewInit(): void {
    if (this.tareaId) {
      this.getTarea(this.tareaId);
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

  getTarea(id) {
    this.TareaService.getTarea(id).pipe(first()).subscribe(
      tarea => {
        this.loadTarea(tarea);
      }
    )
  }

  getHistorial(id){
    this.TareaService.getHistorial(id).subscribe(
      data => {
        this.dataSourceHistorial = data;
      },
      error => {
        console.log(error.error);
        
      }
    );
  }

  getMaquinas(){
    this.MaquinaService.getMaquinas().subscribe(
      (data: any)  => {
        this.dataSourceMaquinas = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.maquina_cod
            }
          }
        );
      },
      (error) => {
        console.error(error);
      }
    );
  }

  getInAddMode(id){
    this.maquinaId =  id.split(" ");
    id =  this.maquinaId[1];
    this.getPartes(id);
  }
  
  getPartes(id){
    this.ParteService.getByMaquina(id).subscribe(
      (data: any) => {
        this.dataSourcePartes = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.nombre + " " + val.codigo
            }
          }
        );
      },
      (error) => {
        console.log(error.error);
      }
    );
  }  


  loadTarea(tarea) {
    this.getPartes(tarea.maquina.id);
    this.getHistorial(tarea.id);
    this.mode = "edit";
    this.section = 'Editar tarea';
    this.buttonName = 'Confirmar cambios';
    this.tareaForm.controls.inicio.setValue(tarea.inicio.replace(' ', 'T'));
    this.tareaForm.controls.frecuencia.setValue(tarea.frecuencia);
    this.tareaForm.controls.maquina_cod.setValue(tarea.maquina.id);
    this.tareaForm.controls.parte_cod.setValue(tarea.parte.id);
    this.tareaForm.controls.tarea.setValue(tarea.tarea);
  }

  saveForm() {
    if (this.mode === 'add') {
      this.TareaService.postTarea(this.tareaForm).subscribe(
        tarea => {
          this.showSuccess();
          this.router.navigate(['main/mantenimientosPreventivos']);
        },
        error => this.showError(error.error)
      );
    } else {
      console.log(this.tareaForm);
      this.TareaService.updateTarea(this.tareaId, this.tareaForm).subscribe(
        tarea => {
          this.messageBody = "La tarea se ha editado correctamente"
          this.showSuccess();
          this.router.navigate(['main/mantenimientosPreventivos']);
        },
        error => this.showError(error.error)
        );
    }
  }



}
