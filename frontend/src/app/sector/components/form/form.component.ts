import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { SectorService } from '../../services/sector.service';
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormSectorComponent implements OnInit {

  public form: FormGroup;
  public sectorId: any;
  public mode = 'add';

  constructor(private SectorService: SectorService,
    private router: Router,
    private route: ActivatedRoute) {
    this.form = this.createFormGroup();
  }

  ngOnInit(): void {
    this.sectorId = this.route.snapshot.params.id;
  }

  ngAfterViewInit(): void {
    if (this.sectorId) {
      this.getSector(this.sectorId);
    }
  }

  getSector(id) {
    this.SectorService.getSector(id).pipe(first()).subscribe(
      sector => {
        this.loadSector(sector);
      }
    )
  }

  loadSector(sector) {
    this.mode = "edit";
    console.log(sector);
    this.form.controls.descripcion.setValue(sector.descripcion);
  }

  createFormGroup() {
    return new FormGroup({
      descripcion: new FormControl(''),
      estado: new FormControl(30)
    })
  }

  resetForm() {
    this.form.reset();
  }

  saveForm() {
    if (this.mode === 'add') {
      this.SectorService.postSector(this.form).subscribe(
        sector => alert("Se ha creado el sector numero: " + sector.id)
      );
    } else {
      this.SectorService.updateSector(this.sectorId, this.form).subscribe(
        sector => {
          console.log(sector);
        });
    }
    this.router.navigate(['main/maquinas'])
  }

}
