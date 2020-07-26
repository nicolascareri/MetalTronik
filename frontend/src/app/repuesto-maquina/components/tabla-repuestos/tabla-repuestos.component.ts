import { Component, OnInit } from '@angular/core';
import { MaquinaService } from "../../../maquina/services/maquina.service";
import { RepuestoMaquinaService } from "../../services/repuesto-maquina.service";
@Component({
  selector: 'app-tabla-repuestos',
  templateUrl: './tabla-repuestos.component.html',
  styleUrls: ['./tabla-repuestos.component.scss']
})
export class TablaRepuestosComponent implements OnInit {

  seleccion: any;

  dataSourceRepuestos: any;

  columnsToDisplay: any = ['maquina.maquina_cod', 'maquina.planta.nombre', 
                          'maquina.sector.descripcion', 'nombre', 'modelo', 'cantidadInstalada'];
  
 

  dataSourceMaquinas: any;



  constructor(private MaquinaService: MaquinaService,
              private RepuestoMaquinaService: RepuestoMaquinaService) { }

  getRepuestos(id){
    
    this.RepuestoMaquinaService.getRepuestosById(id).subscribe(
      (data: any) => {
        this.dataSourceRepuestos = data;
        console.log(this.dataSourceRepuestos)
      },
      (error) => {
        console.log(error);
      }
    );

  }


  ngOnInit(): void {


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
