import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { SectorService } from "../../services/sector.service";

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {

  createFormGroup(){
    return new FormGroup({
      descripcion: new FormControl(''),
      estado: new FormControl(30)
    })
  }

  form: FormGroup;

  constructor(private SectorService: SectorService) 
  {
    this.form  = this.createFormGroup();
   }

  ngOnInit(): void {
  }

  resetForm() {
    this.form.reset();
  }
  
  saveForm() {
    console.log(this.form.value);
    this.SectorService.postSector(this.form).subscribe(
      sector => alert("Se ha creado el sector numero: " + sector.id)
    );
  }

}
