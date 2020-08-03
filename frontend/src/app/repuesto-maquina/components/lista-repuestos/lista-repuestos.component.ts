import { Component, OnInit } from '@angular/core';
import { RepuestoMaquinaService } from "../../services/repuesto-maquina.service";
import { MaquinaService } from "../../../maquina/services/maquina.service";
import { MatListOption } from '@angular/material/list'
import { FormGroup, FormBuilder } from '@angular/forms';



@Component({
  selector: 'app-lista-repuestos',
  templateUrl: './lista-repuestos.component.html',
  styleUrls: ['./lista-repuestos.component.scss']
})
export class ListaRepuestosComponent implements OnInit {

  form: FormGroup;

  dataSourceRepuestos: any;
  dataSourceMaquinas: any;
  

  repuestos: any = [];
  seleccion: any = [];
  maquinaId: any;


  cntInstalada: any;

  asociacion: [
    {
        nombre: " ",
        cantidadInstalada: ' '
    }

  ]

  agruopData(){
    
    const ctrl = this.form.controls;
    
    
    const repuesto = {
      "cantidadInstalada": ctrl.cantidadInstalada.value,
      "repuesto_cod": ctrl.repuesto.value.id
    };

    this.repuestos.push(repuesto);



    const seleccion = {
      "cantidadInstalada": ctrl.cantidadInstalada.value,
      "nombre": ctrl.repuesto.value.nombre
    }
    this.seleccion.push(seleccion);

    
  }
  

  onGroupsChange(options: MatListOption[]) {
    this.repuestos = options.map(o => o.value);
  }


  getMaquinaForSelect(event){
    this.maquinaId = event.id;
    console.log(event.maquina_cod + " Id: " + this.maquinaId);
    
  }

  constructor(private RepuestoMaquinaService: RepuestoMaquinaService,
              private MaquinaService: MaquinaService,
              private formBuilder: FormBuilder) 
  {
  }

  ngOnInit(): void {
    
  
    this.form = this.formBuilder.group({
      repuesto: [''],
      cantidadInstalada: ['']

    });

    this.RepuestoMaquinaService.getRepuestos().subscribe(
      (data: any) => {
        this.dataSourceRepuestos = data;
      },
      (error) => {
        console.log(error);
        
      }
    );

    this.MaquinaService.getMaquinas().subscribe(
      (data: any) => {
        this.dataSourceMaquinas = data;
        console.log(this.dataSourceMaquinas)
      },
      (error) => {
        console.log(error);
      }
    );

    
  
  }

  
 
  
 

}
