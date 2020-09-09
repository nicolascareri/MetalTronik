import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {PlantaService} from '../../services/planta.service';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormPlantaComponent implements OnInit {

  public formPlanta: FormGroup;
  public plantaId: any;
  public mode = 'add';

  constructor(private PlantaService: PlantaService,
              private router: Router,
              private route: ActivatedRoute) {
    this.formPlanta = this.createFormGroup();
  }

  ngOnInit(): void {
    this.plantaId = this.route.snapshot.params.id;
  }

  ngAfterViewInit(): void {
    if (this.plantaId) {
      this.getPlanta(this.plantaId);
    }
  }

  getPlanta(id){
    this.PlantaService.getPlanta(id).pipe(first()).subscribe(
      planta => {
        this.loadPlanta(planta);
      }
    )
  }

  loadPlanta(planta) {
    this.mode = "edit";
    console.log(planta);
    this.formPlanta.controls.nombre.setValue(planta.nombre);
  }

  createFormGroup() {
    return new FormGroup({
      nombre: new FormControl(''),
      estado: new FormControl(30)
    })
  }


  resetForm() {
    this.formPlanta.reset();
  }

  saveForm() {
    if (this.mode === 'add') {
    this.PlantaService.postPlanta(this.formPlanta).subscribe(
      planta => alert("Se ha creado la planta numero: " + planta.id)
    );
  } else {
    this.PlantaService.updatePlanta(this.plantaId, this.formPlanta).subscribe(
      planta => {
        console.log(planta);
      });
  }   
  this.router.navigate(['main/maquinas'])
  }

}
