import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {SectorService} from '../../services/sector.service';

@Component({
  selector: 'app-tabla-sector',
  templateUrl: './tabla-sector.component.html',
  styleUrls: ['./tabla-sector.component.scss']
})
export class TablaSectorComponent implements OnInit {

  columnsToDisplay: any = ['descripcion'];

  dataSourceSectors: any;

  form: FormGroup;

  createFormGroup(){
    return new FormGroup({
      descripcion: new FormControl('')
    })
  }


  constructor(private SectorService: SectorService) {
    this.form = this.createFormGroup();
  }

  ngOnInit(): void {

    this.SectorService.getSectores().subscribe(
      (data: any) => {
        this.dataSourceSectors = data;
        console.log(this.dataSourceSectors)
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
