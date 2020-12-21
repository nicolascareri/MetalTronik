import { Component, OnInit } from '@angular/core';
import { RepuestoMaquinaService } from "../../services/repuesto-maquina.service";
import { MaquinaService } from "../../../maquina/services/maquina.service";
import { FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { ParteService } from "../../../maquina/services/parte.service";

@Component({
  selector: 'app-lista-repuestos',
  templateUrl: './lista-repuestos.component.html',
  styleUrls: ['./lista-repuestos.component.scss']
})

export class ListaRepuestosComponent implements OnInit {

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  public dataSourceRepuestos: any = [];
  public dataSourceMaquinas: any;
  public dataSourcePartes: any;
  public repuestos: any = [];
  public modelos: any = [];
  public modeloValue: any;
  public seleccion: any = [];
  public maquinaId: any;
  public maquinaPart: any;
  public partId: any;
  public cntInstalada: any;
  public idSeleccion = 0;
  public repuestosFilter: any;
  public maquina: FormGroup = new FormGroup({
    maquina_id: new FormControl(''),
    parte_id: new FormControl(''),
  });
  public form: FormGroup;
  public requestList: any = [];


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


  constructor(private RepuestoMaquinaService: RepuestoMaquinaService,
              private MaquinaService: MaquinaService,
              private formBuilder: FormBuilder,
              private ParteService: ParteService) 
  {}

  ngOnInit(): void {

    this.getRepuestos();
    this.getMaquinas();

    this.form = this.formBuilder.group({
      repuesto_id: [''],
      cantidad_instalada: ['']
    });

  }
  
  asociar() {
    let request = {
      'maquina_id': this.maquinaPart,
      'parte_id' : this.partId,
      'requestList': this.seleccion
    }

    console.log(request);
    
    this.RepuestoMaquinaService.asociarRepuestos(request).subscribe(
      repuestos => {
        
        
        alert("Exitos:")
    },
    (error) => {
     
      console.log(request);
      console.log(error.error);
    }

      );
  }
  

  agruopData() {
    const ctrl = this.form.controls;
    
    
    const repuesto = {
      "cantidad_instalada": ctrl.cantidad_instalada.value,
      "repuesto_id": ctrl.repuesto_id.value
    };
  
    this.repuestos.push(repuesto);

    const seleccion = {
      "cantidad_instalada": ctrl.cantidad_instalada.value,
      "repuesto_id": ctrl.repuesto_id.value,
    }


    this.seleccion.push(seleccion);
    this.requestList.push(this.repuestos);

    console.log(this.requestList);

    console.log(this.seleccion);
    
    

  }


  deleteSelection() {
    this.seleccion.pop();
  }

  getMachineId(id){
    this.maquinaPart = id.split(" ");
    id = this.maquinaPart[1];
    this.getParts(id);
    this.maquinaPart = id;
  }

  getPartId(id){
    this.partId = id.split(" ");
    id = this.partId[1];
    this.partId = id;
  }



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  getRepuestos(){
    this.RepuestoMaquinaService.getRepuestos().subscribe(
      (data: any) => {
        this.dataSourceRepuestos = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.codigoProducto + " - " + val.nombre
            }
          }
        );
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
        this.dataSourceMaquinas = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.maquina_cod + " - " + val.equipo
            }
          }
        );
      },
      (error) => {
        console.log(error.error);
        
      }
    );
    console.log(this.requestList);
  }


  getParts(id){
    this.ParteService.getByMaquina(id).subscribe(
      (data: any) => {
        this.dataSourcePartes = data.map(
          val => {
            return {
              "id": val.id,
              "descripcion": val.nombre + " - " + val.codigo
            }
          }
        );
      },
      (error) => {
        console.error(error);
      }
    );
  }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
    
    
  }







