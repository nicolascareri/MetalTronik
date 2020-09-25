import { Component, OnInit } from '@angular/core';
import { RepuestoMaquinaService } from "../../services/repuesto-maquina.service";
import { CoreService } from 'src/app/core/service/core.service';
import { MessageService } from "../../../core/service/message.service";
import { MaquinaService } from "../../../maquina/services/maquina.service";

@Component({
  selector: 'app-tabla-repuestos',
  templateUrl: './tabla-repuestos.component.html',
  styleUrls: ['./tabla-repuestos.component.scss']
})

export class TablaRepuestosComponent implements OnInit {

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
  public messageBody: any = "Repuesto asociado correctamente";
  public alerta = this.showWarning("La maquina seleccionada no tiene repuestos asociados.");


  public columnsToDisplay: any[] = [
    {
      id: 1,
      property: 'nombre',
      name: 'Nombre',
      sort: 'up',
      filterValue: '',
      width: '15%'
    },
    {
      id: 2,
      property: 'modelo',
      name: 'Modelo',
      sort: '',
      filterValue: '',
      width: '20%'
    },
    {
      id: 3,
      property: 'cantidad_instalada',
      name: 'Cantidad instalada',
      sort: '',
      filterValue: '',
      width: '20%'
    },
    {
      id: 4,
      property: 'maquina',
      name: 'Codigo maquina',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 5,
      property: 'planta',
      name: 'Planta',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 6,
      property: 'sector',
      name: 'Sector',
      sort: '',
      filterValue: '',
      width: '15%'
    }
  ];

  constructor(private RepuestoMaquinaService: RepuestoMaquinaService,
              private coreService: CoreService,
              private MessageService: MessageService,
              private MaquinaService: MaquinaService) { }


  ngOnInit(): void {
    this.getRepuestosMaquina();
    this.getMaquinas();
  }

  showWarning(messageBody){
    return this.MessageService.showWarning({
      title: this.messageTitleWarning,
      body: this.messageBody = messageBody
    });
  }

  getMaquinas(){
    this.MaquinaService.getMaquinas().subscribe(
      (data: any) => {
        this.dataSourceMaquinas = data;
        data.forEach(element => {
          
          this.maquinasSinRepuestos.push(element);  
          console.log(this.maquinasSinRepuestos);
          
            
          }
        );
      },
      (error) => {
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
        this.cantidadTotal = 0;
        data.forEach(element => {

          this.maquinasSinRepuestos.filter(word => this.maquinasSinRepuestos.includes(element.maquina));
          this.maquinasSinRepuestoss = this.maquinasSinRepuestos;
          this.cantidadTotal += element.cantidad_instalada;
            // if (this.maquinasSinRepuestos.includes(element.maquina)) {
            //   this.maquinasSinRepuestos.pop(element.maquina)
            // }
            
            
        });

       
       
      console.log(this.maquinasSinRepuestoss);
        
      this.dataSourceRespuestoMaquina = this.coreService.replaceFormat(data, ['nombre', 'modelo', 'maquina']);
      },
      (error) => {
        console.error(error);

      }
    );
  }


}
