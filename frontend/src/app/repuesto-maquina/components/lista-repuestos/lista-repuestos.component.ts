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

  public form: FormGroup;
  public dataSourceRepuestos: any = [];
  public dataSourceMaquinas: any;
  public repuestos: any = [];
  public modelos: any = [];
  public modeloValue: any;
  public seleccion: any = [];
  public seleccionRepuesto: String = 'all';
  public maquinaId: any;
  public cntInstalada: any;
  public idSeleccion = 0;
  public repuestosFilter: any;


  constructor(private RepuestoMaquinaService: RepuestoMaquinaService,
              private MaquinaService: MaquinaService,
              private formBuilder: FormBuilder) 
  {}

  ngOnInit(): void {

    this.getRepuestos();
    this.getMaquinas();

    this.form = this.formBuilder.group({
      repuesto: [''],
      cantidad_instalada: ['']
    });

  }
  
  asociar() {
    this.RepuestoMaquinaService.asociarRepuestos(this.maquinaId, this.repuestos).subscribe(repuestos => alert("Exitos:" + repuestos));
  }
  
  
  agruopData() {
    const ctrl = this.form.controls;
    const repuesto = {
      "cantidad_instalada": ctrl.cantidad_instalada.value,
      "repuesto_cod": ctrl.repuesto.value.id
    };
  
    this.repuestos.push(repuesto);

    const seleccion = {
      "id": this.idSeleccion,
      "cantidad_instalada": ctrl.cantidad_instalada.value,
      "nombre": ctrl.repuesto.value.nombre
    }

    this.idSeleccion++;
    this.seleccion.push(seleccion);

  }


  getMaquinaForSelect(event) {
    this.maquinaId = event.id;
  }

  deleteSelection() {
    this.seleccion.pop();
  }

  filtrarPorModelo(m){
   this.seleccionRepuesto = ' ';
   this.repuestosFilter = this.dataSourceRepuestos.filter(r => r.modelo == m);
  //  this.seleccionRepuesto = 'all';
  //  console.log(this.repuestosFilter);
   
  }

  getRepuestos(){
    this.RepuestoMaquinaService.getRepuestos().subscribe(
      (data: any) => {
        this.dataSourceRepuestos = data 
        data.forEach(repuesto => {
          if (!this.modelos.includes(repuesto.modelo)) {
            this.modelos.push(repuesto.modelo)
          }
          
        });
      },

      (error) => {
        console.error(error);

      }
      
    );
  }

  getMaquinas(){
    this.MaquinaService.getMaquinas().subscribe(
      (data: any) => {
        this.dataSourceMaquinas = data;
      },
      (error) => {
      }
    );
    console.log(this.seleccion);
  }

  changeDataSource(){
    let dataSource;   
    if(this.seleccionRepuesto == 'all'){
      dataSource = this.dataSourceRepuestos
    }else{
      dataSource = this.repuestosFilter;
    }
    return dataSource;
  }
    
    
    
    
    
    
  }







