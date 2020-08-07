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

  cantidad: any = 0;;
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
       

          data.forEach(element => {
            if(element.maquina){
            this.cantidadTotal += element.cantidadInstalada;
            }
          });
        
        this.dataSourceAllRepuestos = data;
      },
      (error) => {
        console.log(error);
        
      }
    );



    

    this.MaquinaService.getMaquinas().subscribe(
      (data: any) => {
        this.dataSourceMaquinas = data;
        // console.log(this.dataSourceMaquinas)
      },
      (error) => {
        console.log(error);
      }
    );
  }




  getRepuestosByMaquina(id){
    this.cantidad = 0;
    this.RepuestoMaquinaService.getRepuestosById(id).subscribe(
      (data: any) => {
        // data.map(repuesto => {
        //   if (repuesto.maquina) {
        //     this.cantidad += repuesto.cantidadInstalada;
        //   }
        // });
        data.forEach(element => {
          if(element.maquina){
          
          this.cantidad += element.cantidadInstalada;
          }
        });
      
        this.dataSourceRepuestos = data;
        console.log(this.dataSourceRepuestos)
        
      },
      (error) => {
        console.log(error);
      }
    );

  }

  changeDataSource(){
    let dataSource;   
    if (this.seleccion == 'all') {
      dataSource = this.dataSourceAllRepuestos;
      // this.cantidad = 0;
      // dataSource.map(repuesto => {
      //   if (repuesto.maquina) {
      //     this.cantidad += repuesto.cantidadInstalada;
      //   }
      // });
      // // this.cantidad += this.dataSourceAllRepuestos.data.forEach(element => {
      // //   element.cantidadInstalada;
      // });;
    }else{
      dataSource = this.dataSourceRepuestos;
      // this.cantidadTotal = this.dataSourceRepuestos.length;
    }
    return dataSource;
  }


}
