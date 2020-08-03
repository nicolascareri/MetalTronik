import { Component, OnInit } from '@angular/core';
import { RepuestoMaquinaService } from "../../services/repuesto-maquina.service";
import { MaquinaService } from "../../../maquina/services/maquina.service";
import { MatListOption } from '@angular/material/list'
import { FormGroup, FormBuilder, FormArray, FormControl } from '@angular/forms';



@Component({
  selector: 'app-lista-repuestos',
  templateUrl: './lista-repuestos.component.html',
  styleUrls: ['./lista-repuestos.component.scss']
})
export class ListaRepuestosComponent implements OnInit {

  form: FormGroup;

  createFormGroup() {
    return new FormGroup({
      repuesto_cod: new FormControl(''),
      cantidadInstalada: new FormControl('')
    })
  }


  dataSourceRepuestos: any;
  dataSourceMaquinas: any;
  

  repuestos: any[];
  maquinaId: any;


  cntInstalada: any;

  asociacion: [
    {
        nombre: " ",
        cantidadInstalada: ' '
    }

  ]

  agruopData(){

    const repuesto = {
      "cantidadInstalada": '',
      "repuesto_cod": ''
    };

    // this.repuestos.forEach(repuesto => repuesto.cantidadInstalada = this.cntInstalada);
    // console.log(this.cntInstalada);
    
    
    this.repuestos.forEach(repuesto => console.log(repuesto.nombre + " " + repuesto.cantidadInstalada));
    
    
  }
  

  onGroupsChange(options: MatListOption[]) {
    this.repuestos = options.map(o => o.value);
    // console.log(options.map(o => o.value));
    // console.log("Hijole: " + this.repuestos);


    
   
    this.repuestos.forEach(repuesto => console.log(repuesto.nombre + " " + repuesto.cantidadInstalada));
  }


  getMaquinaForSelect(event){
    this.maquinaId = event.id;
    console.log(event.maquina_cod + " Id: " + this.maquinaId);
    
  }






  constructor(private RepuestoMaquinaService: RepuestoMaquinaService,
              private MaquinaService: MaquinaService,
              private formBuilder: FormBuilder) 
  {
    this.form = this.createFormGroup();
  }

  ngOnInit(): void {
    
    
    this.form = this.formBuilder.group({

      repuesto: ['']

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
