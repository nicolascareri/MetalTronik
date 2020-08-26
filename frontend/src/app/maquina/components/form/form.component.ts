import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {MaquinaService} from '../../services/maquina.service';
import {PlantaService} from '../../../planta/services/planta.service';
import {SectorService} from '../../../sector/services/sector.service';

@Component({
  selector: 'app-form-maquina',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormMaquinaComponent implements OnInit {


  dataSourcePlants: any;
  dataSourceSectors: any;

  
  machinesForm: FormGroup = new FormGroup({
    maquina_cod: new FormControl(''),
    nro_serie: new FormControl(''),
    modelo: new FormControl(''),
    equipo: new FormControl(''),
    datos_tecnicos: new FormControl(''),
    descripcion: new FormControl(''),
    planta_cod: new FormControl(''),
    sector_cod: new FormControl(''),
    estado: new FormControl(30)
  });

  constructor(private MaquinaService: MaquinaService,
              private PlantaService: PlantaService,
              private SectorService: SectorService)
  {
    
  }

  ngOnInit(): void {

    this.PlantaService.getPlantas().subscribe(

      (data: any)  => { // Success
        this.dataSourcePlants = data.map(
          val => { return {
              "id": val.id,
              "descripcion": val.nombre
            }
          }
        );
        console.log(this.dataSourcePlants);
      },
      (error) => {
        console.error(error);
      }

    );

    this.SectorService.getSectores().subscribe(

      (data: any)  => { // Success
        this.dataSourceSectors = data.map(
          val => { return {
              "id": val.id,
              "descripcion": val.descripcion
            }
          }
        );
        console.log(this.dataSourceSectors);
      },
      (error) => {
        console.error(error);
      }

    );

  }



  resetForm() {
    this.machinesForm.reset();
  }

  saveForm() {
    console.log(this.machinesForm.value);
    this.MaquinaService.postMaquina(this.machinesForm).subscribe(
      maquina => alert("Se ha creado la maquina numero: " + maquina.id)
    );
    //this.router.navigate(['main/ordenes'])


  }

}
