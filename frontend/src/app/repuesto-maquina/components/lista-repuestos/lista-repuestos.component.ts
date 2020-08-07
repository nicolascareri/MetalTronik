import { Component, OnInit } from '@angular/core';
import { RepuestoMaquinaService } from "../../services/repuesto-maquina.service";
import { MaquinaService } from "../../../maquina/services/maquina.service";
import { FormGroup, FormBuilder } from '@angular/forms';




@Component({
  selector: 'app-lista-repuestos',
  templateUrl: './lista-repuestos.component.html',
  styleUrls: ['./lista-repuestos.component.scss']
})
export class ListaRepuestosComponent implements OnInit {

  form: FormGroup;

 
  dataSourceRepuestos: any = [];
  dataSourceMaquinas: any;
  


  repuestos: any = [];
  modelos: any = [];
  modeloValue: any;
  seleccion: any = [];
  maquinaId: any;
  cntInstalada: any;
  idSeleccion = 0;
  repuestosFilter: any;


  constructor(private RepuestoMaquinaService: RepuestoMaquinaService,
    private MaquinaService: MaquinaService,
    private formBuilder: FormBuilder) {
  }

  ngOnInit(): void {

    

    this.form = this.formBuilder.group({
      repuesto: [''],
      cantidadInstalada: ['']
    });

    this.RepuestoMaquinaService.getRepuestos().subscribe(
      (data: any) => {
        this.dataSourceRepuestos = data;

        
        data.forEach(element => {
          this.modelos.push(element.modelo)
        });
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

  asociar() {
    this.RepuestoMaquinaService.asociarRepuestos(this.maquinaId, this.repuestos).subscribe(repuestos => alert("Exitos" + repuestos));
  }


  agruopData() {

    const ctrl = this.form.controls;

    const repuesto = {
      "cantidadInstalada": ctrl.cantidadInstalada.value,
      "repuesto_cod": ctrl.repuesto.value.id
    };

    this.repuestos.push(repuesto);

    const seleccion = {
      "id": this.idSeleccion,
      "cantidadInstalada": ctrl.cantidadInstalada.value,
      "nombre": ctrl.repuesto.value.nombre
    }

    this.idSeleccion++;
    this.seleccion.push(seleccion);

    console.log(this.seleccion);


  }


  getMaquinaForSelect(event) {
    this.maquinaId = event.id;
    console.log(event.maquina_cod + " Id: " + this.maquinaId);
  }

  deleteSelection(id) {
    this.seleccion.splice(id, 1);
    console.log(this.seleccion);
  }

  obtenerModelos(){
   this.repuestosFilter = this.dataSourceRepuestos.filter(r => r.modelo == this.modeloValue); 
  }
    
    
    
    
    
    
  }







