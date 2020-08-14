import { Component, OnInit } from '@angular/core';
import {PrioridadesService} from "../../services/prioridades.service";
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-lista-prioridades',
  templateUrl: './lista-prioridades.component.html',
  styleUrls: ['./lista-prioridades.component.scss']
})
export class ListaPrioridadesComponent implements OnInit {

  dataSourcePrioridades: any;

  form: FormGroup;

  createFormGroup() {
    return new FormGroup({
      nombre: new FormControl(''),
      estado: new FormControl(30)
    })
  }

  constructor(private PrioridadesService: PrioridadesService) 
  {
    this.form = this.createFormGroup();
  }

  ngOnInit(): void {

    this.PrioridadesService.getPrioridades().subscribe(
      (data: any) => {
        this.dataSourcePrioridades = data;
      },
      (error) => {
      }
    );

  }

  addPrioridad() {
    this.PrioridadesService.postPrioridad(this.form).subscribe(
      prioridad =>  location.reload()
      
    );
  }

}
