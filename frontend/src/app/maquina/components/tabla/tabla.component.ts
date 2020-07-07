import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { FormGroup, FormControl } from "@angular/forms";
import { MaquinaService } from "../../services/maquina.service";


@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaComponent implements OnInit {

  columnsToDisplay: any = ['id','maquina_cod','nro_serie','modelo','equipo','datos_tecnicos','descripcion',
                           'planta.nombre','sector.descripcion' ];

  dataSourceMachines: any;
  form: FormGroup;
  

  createFormGroup(){
    return new FormGroup({
      id: new FormControl(''),
      maquina_cod: new FormControl('')
    })
  }

  //Editar
  @Input() originalUser: any; 
  @Output() close = new EventEmitter();

  constructor(private MaquinaService: MaquinaService) {
    this.form = this.createFormGroup();
   }

   applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSourceMachines.filter = filterValue.trim().toLowerCase();
    console.log(this.dataSourceMachines);
    console.log(this.dataSourceMachines.filter);
    
  }


  ngOnInit(): void {

    this.MaquinaService.getMaquinas().subscribe(

      (data: any)  => { // Success
        this.dataSourceMachines = data;
        console.log(this.dataSourceMachines);
      },
      (error) => {
        console.error(error);
      }

    );
  }

}
