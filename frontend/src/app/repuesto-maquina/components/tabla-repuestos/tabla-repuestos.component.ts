import { Component, OnInit } from '@angular/core';
import { MaquinaService } from "../../../maquina/services/maquina.service";
import { RepuestoMaquinaService } from "../../services/repuesto-maquina.service";
import { CoreService } from 'src/app/core/service/core.service';
import { MessageService } from "../../../core/service/message.service";

@Component({
  selector: 'app-tabla-repuestos',
  templateUrl: './tabla-repuestos.component.html',
  styleUrls: ['./tabla-repuestos.component.scss']
})
export class TablaRepuestosComponent implements OnInit {

  public seleccion: any = 'all';
  public dataSourceRepuestos: any;
  public dataSourceAllRepuestos: any;
  public dataSourceMaquinasSinRepuestos: any;
  public dataSourceMaquinas: any;
  public cantidad: any = 0;
  public cantidadTotal: any = 0;
  public messageTitleSuccess: any = "DONE";
  public messageTitleError: any = "ERROR";
  public messageTitleWarning: any = "Warning!";
  public messageBody: any = "La orden se ha creado correctamente";
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
      property: 'cantidadInstalada',
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

  constructor(private MaquinaService: MaquinaService,
              private RepuestoMaquinaService: RepuestoMaquinaService,
              private coreService: CoreService,
              private MessageService: MessageService) { }


  ngOnInit(): void {

    this.getRepuestos();
    this.getMaquinas();
    this.getMaquinasSinRepuestos();

  }

  showWarning(messageBody){
    return this.MessageService.showWarning({
      title: this.messageTitleWarning,
      body: this.messageBody = messageBody
    });
  }


  getRepuestosByMaquina(id) {
    this.cantidad = 0;
    this.RepuestoMaquinaService.getRepuestosById(id).subscribe(
      (data: any) => {
        data.forEach(element => {
          if (element.maquina) {
            this.cantidad += element.cantidadInstalada;
          }
        });
        this.dataSourceRepuestos = data;
      },
      (error) => {
      }
    );

  }

  getMaquinasSinRepuestos(){
    this.MaquinaService.getMaquinasSinRepuestos().subscribe(
      (data: any) => {
        this.dataSourceMaquinasSinRepuestos = data;
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

        this.dataSourceAllRepuestos = this.coreService.replaceFormat(data, ['maquina']);
      },
      (error) => {
        console.error(error);

      }
    );
  }

  changeDataSource() {
    let dataSource;
    if (this.seleccion == 'all') {
      dataSource = this.dataSourceAllRepuestos;
    } else {
      dataSource = this.dataSourceRepuestos;
    }
    return dataSource;
  }



}
