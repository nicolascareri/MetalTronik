import { Component, OnInit } from '@angular/core';
import { RepuestoMaquinaService } from "../../services/repuesto-maquina.service";
import { CoreService } from 'src/app/core/service/core.service';
import { MessageService } from "../../../core/service/message.service";
import { MaquinaService } from "../../../maquina/services/maquina.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-tabla-repuestos',
  templateUrl: './tabla-repuestos.component.html',
  styleUrls: ['./tabla-repuestos.component.scss']
})

export class TablaRepuestosComponent implements OnInit {

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

  public seleccion: any = 'all';
  
  public dataSourceRepuestos: any;
  public dataSourceAllRepuestos: any;
  public Maquinas: any;
  public cantidad: any = 0;
  public cantidadTotal: any = 0;

  public dataSourceMaquinas: any;
  public maquinasSinRepuestos:  any = [];
  public maquinasSinRepuestoss:  any = [];
  public dataSourceRespuestoMaquina;
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageTitleWarning: any = "Warning!";
  public messageBody: any = "Las siguientes maquinas no tienen repuestos asociados";



  public columnsToDisplay: any[] = [
    {
      id: 1,
      property: 'repuesto_codigo',
      name: 'Código repuesto',
      sort: 'up',
      filterValue: '',
      width: '20%'
    },
    {
      id: 2,
      property: 'repuesto_modelo',
      name: 'Nombre',
      sort: 'up',
      filterValue: '',
      width: '25%'
    },
    {
      id: 3,
      property: 'repuesto_nombre',
      name: 'Modelo',
      sort: '',
      filterValue: '',
      width: '25%'
    },
    {
      id: 4,
      property: 'cantidad_instalada',
      name: 'Cantidad instalada',
      sort: '',
      filterValue: '',
      width: '20%'
    },
    {
      id: 5,
      property: 'maquina_codigo',
      name: 'Código maquina',
      sort: '',
      filterValue: '',
      width: '25%'
    },
    {
      id: 6,
      property: 'maquina_nombre',
      name: 'Nombre',
      sort: '',
      filterValue: '',
      width: '25%'
    },
    {
      id: 7,
      property: 'parte_nombre',
      name: 'Parte de máquina',
      sort: '',
      filterValue: '',
      width: '25%'
    },
    {
      id: 8,
      property: 'maquina_planta',
      name: 'Planta',
      sort: '',
      filterValue: '',
      width: '25%'
    },
    {
      id: 9,
      property: 'maquina_sector',
      name: 'Sector',
      sort: '',
      filterValue: '',
      width: '25%'
    },
    {
      id: 10,
      property: 'observaciones',
      name: 'Observaciones',
      sort: '',
      filterValue: '',
      width: '40%'
    }
  ];

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  constructor(private RepuestoMaquinaService: RepuestoMaquinaService,
              private coreService: CoreService,
              private router: Router,
              private MessageService: MessageService,
              private MaquinaService: MaquinaService) { }


  ngOnInit(): void {
    this.getRepuestosMaquina();
    this.getMaquinas();
    this.getMaquinasSinAsoc();
  }

  clickedRow(row){
    this.router.navigate(['main/repuestos/lista-repuestos/' +  row.id]);
  }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  showWarning(messageBody){
    return this.MessageService.showWarning({
      title: this.messageTitleWarning,
      body: this.messageBody = messageBody
    });
  }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  
  getMaquinas(){
    this.MaquinaService.getMaquinas().subscribe(
      (data: any) => {
        this.dataSourceMaquinas = data;
        // data.forEach(element => {
          
        //   this.maquinasSinRepuestos.push(element);  
        //   console.log(this.maquinasSinRepuestos);
          
            
        //   }
        // );
      },
      (error) => {
        console.log(error.error);
        
      }
    );
  }

  getRepuestos(){
    this.RepuestoMaquinaService.getRepuestos().subscribe(

      (data: any) => {
        this.cantidadTotal = 0;
        data.forEach(element => {
          if (element.maquina) {
            this.cantidadTotal += element.cantidadInstalada;
            
          }
        });

        this.dataSourceAllRepuestos = this.coreService.replaceFormat(data, ['maquina', 'modelo', 'cantidad_instalada']);
      },
      (error) => {
        console.error(error);

      }
    );
  }

  getRepuestosMaquina(){
    this.RepuestoMaquinaService.getRepuestoMaquina().subscribe(
      (data: any) => {
        // this.cantidadTotal = 0;
        // data.forEach(element => {

        //   this.maquinasSinRepuestos.filter(word => this.maquinasSinRepuestos.includes(element.maquina));
        //   this.maquinasSinRepuestoss = this.maquinasSinRepuestos;
        //   this.cantidadTotal += element.cantidad_instalada;
        //     // if (this.maquinasSinRepuestos.includes(element.maquina)) {
        //     //   this.maquinasSinRepuestos.pop(element.maquina)
        //     // }            
        // });
      this.dataSourceRespuestoMaquina = this.coreService.replaceFormat(data, ['nombre', 'modelo', 'maquina']);
      },
      (error) => {
        console.error(error);

      }
    );
  }

  getMaquinasSinAsoc(){
    let maquinas: any = [];
    this.RepuestoMaquinaService.getMaquinasSinAsoc().subscribe(
      (data: any) => {
          data.forEach(element => {
            maquinas.push(element.maquina_cod);
          });
          this.showWarning(this.messageBody + ": " + maquinas);
      },
      (error) => {
        console.log(error.error);
      }
    );
  }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
