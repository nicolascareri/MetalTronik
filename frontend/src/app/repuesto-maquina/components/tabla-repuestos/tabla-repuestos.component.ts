import { Component, OnInit } from '@angular/core';
import { MaquinaService } from "../../../maquina/services/maquina.service";
import { RepuestoMaquinaService } from "../../services/repuesto-maquina.service";

@Component({
  selector: 'app-tabla-repuestos',
  templateUrl: './tabla-repuestos.component.html',
  styleUrls: ['./tabla-repuestos.component.scss']
})
export class TablaRepuestosComponent implements OnInit {

  seleccion: any = 'all';

  dataSourceRepuestos: any;
  dataSourceAllRepuestos: any;
  dataSourceMaquinasSinRepuestos: any;

  cantidad: any = 0;
  cantidadTotal: any = 0;

  columnsToDisplay: any = ['nombre', 'modelo', 'cantidadInstalada', 'maquina.maquina_cod', 'maquina.planta.nombre', 
                          'maquina.sector.descripcion'];
  
 

  dataSourceMaquinas: any;



  constructor(private MaquinaService: MaquinaService,
              private RepuestoMaquinaService: RepuestoMaquinaService) { }
  

  ngOnInit(): void {

    this.RepuestoMaquinaService.getRepuestos().subscribe(
      
      (data: any) => {
        // data.map(repuesto => {
        //   if (repuesto.maquina) {
        //     this.cantidad += repuesto.cantidadInstalada;
        //   }
        // });
       
          this.cantidadTotal = 0;
          data.forEach(element => {
            if(element.maquina){
            this.cantidadTotal += element.cantidadInstalada;
            }
          });
        
        this.dataSourceAllRepuestos = data;
      },
      (error) => {
        console.error(error);
        
      }
    );



    

    this.MaquinaService.getMaquinas().subscribe(
      (data: any) => {
        this.dataSourceMaquinas = data;
      },
      (error) => {
      }
    );

    this.MaquinaService.getMaquinasSinRepuestos().subscribe(
      (data: any) => {
        this.dataSourceMaquinasSinRepuestos = data;
      },
      (error) => {
        console.error(error);
      }
    );

  }

  
  getRepuestosByMaquina(id){
    this.cantidad = 0;
    this.RepuestoMaquinaService.getRepuestosById(id).subscribe(
      (data: any) => {
        data.forEach(element => {
          if(element.maquina){
          this.cantidad += element.cantidadInstalada;
          }
        });
        this.dataSourceRepuestos = data;
      },
      (error) => {
      }
    );

  }

  changeDataSource(){
    let dataSource;   
    if (this.seleccion == 'all') {
      dataSource = this.dataSourceAllRepuestos;
    }else{
      dataSource = this.dataSourceRepuestos;
    }
    return dataSource;
  }
}
