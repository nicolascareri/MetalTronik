import {Component, OnInit} from '@angular/core';
import {MaquinaService} from '../../services/maquina.service';
import { ParteService } from "../../services/parte.service";
import { CoreService } from 'src/app/core/service/core.service';
import { Router } from '@angular/router';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-tabla-maquina',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})

export class TablaMaquinaComponent implements OnInit {

  public columnsToDisplay: any[] = [
    {
      id: 1,
      property:'planta',
      name: 'Planta',
      sort: 'up',
      filterValue: '',
      width: '15%'
    }, 
    {
      id: 2,
      property:'sector',
      name: 'Sector',
      sort: '',
      filterValue: '',
      width: '14%'
    },
    {
      id: 3,
      property:'maquina_cod',
      name: 'Codigo de maquina',
      sort: '',
      filterValue: '',
      width: '20%'
    }, 
    {
      id: 4,
      property:'equipo',
      name: 'Equipo',
      sort: '',
      filterValue: '',
      width: '15%'
    },
    {
      id: 5,
      property:'descripcion',
      name: 'Descripcion',
      sort: '',
      filterValue: '',
      width: '350px'
    }
  ];
  public dataSourceMachines;
  public dataSourcePartes;

  //Modal variables
  public machineModal;
  title = 'appBootstrap';
  closeResult: string;
  


  constructor(private MaquinaService: MaquinaService,
              private coreService: CoreService,
              private router: Router,
              private modalService: NgbModal,
              private ParteService: ParteService) {
   }

   open(content, row) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
    this.clickedRow(row);
  }
  
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return  `with: ${reason}`;
    }
  }


  ngOnInit(): void {
    this.getMaquinas();
  }


  clickedRow(row){
    let maquina = {
      "id": row.id,
      "maquina_cod": row.maquina_cod,
      "equipo": row.equipo,
      "planta": row.planta,
      "sector": row.sector,
      "descripcion": row.descripcion
    };
    this.machineModal = maquina;
    this.getPartesAsoc(this.machineModal.id);
  }

  getMaquinas(){
    this.MaquinaService.getMaquinas().subscribe(
      (data: any)  => {
        this.dataSourceMachines = this.coreService.replaceFormat(data, ['maquina', 'planta', 'sector']);
      },
      (error) => {
        console.error(error);
      }
    );
  }

  getPartesAsoc(id){
    this.ParteService.getByMaquina(id).subscribe(
      (data: any) => {
        this.dataSourcePartes = data.map(
          val => {
            return {
              "id": val.id,
              "codigo": val.codigo,
              "nombre": val.nombre
            }
          }
        );
      },
      (error) => {
        console.error(error);
      }
    );
  }

  editMaquina(id){
    this.router.navigate(['main/maquinas/form/' + id]);
    this.modalService.dismissAll();

  }

}
