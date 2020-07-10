import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl } from "@angular/forms";
import { MantenimientoCorrectivoService } from "../../services/mantenimiento-correctivo.service";

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaComponent implements OnInit {

  dataSourceMantenimientosCorrectivos: any;

  form: FormGroup;

  columnsToDisplay: any = ['nrocorrectivo', 'ordenTrabajo.ordentrabajo_cod', 'ordenTrabajo.tipo.nombre', 
                           'ordenTrabajo.maquina.sector.descripcion', 'tipofalla', 'fechainicio', 'fechaFin', 'tiempoReparacion', 
                           'horasProduccionAfectadas', 'observaciones', 'encargo1', 'encargo2', 'encargo3'
                          ];

  
  constructor(private MantenimientoCorrectivoService: MantenimientoCorrectivoService) { }


  ngOnInit(): void {

    this.MantenimientoCorrectivoService.getMantenimientosCorrectivos().subscribe(

      (data: any)  => { // Success
        this.dataSourceMantenimientosCorrectivos = data;
        console.log(this.dataSourceMantenimientosCorrectivos);
      },
      (error) => {
        console.error(error);
      }

    );


  }

}
