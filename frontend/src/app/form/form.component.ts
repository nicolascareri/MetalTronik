import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import { UserService } from "../user.service";
import { OrdenestrabajoService } from "../ordenestrabajo.service";


@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {


  createFormGroup(){
    return new FormGroup({
      codigo_encargo: new FormControl(''),
      estado: new FormControl(''),
      fecha_realizar: new FormControl(''),
      pedido_materiales: new FormControl(''),
      planta: new FormControl(''),
      prioridad: new FormControl(''),
      codigo_responsable: new FormControl(''),
      codigo_tarea: new FormControl(''),
      tipo: new FormControl(''),
      
    })
  }

  ordenForm: FormGroup;


  constructor(private OrdenestrabajoService: OrdenestrabajoService) {
    this.ordenForm = this.createFormGroup();
   }

  ngOnInit(): void {
    
  }

  resetForm() {
    this.ordenForm.reset();
  }

  saveForm() {
    console.log(this.ordenForm.value);
    this.OrdenestrabajoService.postOrder(this.OrdenestrabajoService).subscribe(user => console.log(user)
    );
  }

}
