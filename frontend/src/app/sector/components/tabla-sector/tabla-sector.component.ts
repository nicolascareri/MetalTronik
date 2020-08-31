import {Component, OnInit} from '@angular/core';
import {SectorService} from '../../services/sector.service';

@Component({
  selector: 'app-tabla-sector',
  templateUrl: './tabla-sector.component.html',
  styleUrls: ['./tabla-sector.component.scss']
})
export class TablaSectorComponent implements OnInit {

  public columnsToDisplay: any[] = [
    {
      id: 1,
      property:'descripcion',
      name: 'Nombre del sector',
      sort: 'up',
      filterValue: '',
      width: '15%'
    }
  ];

  public dataSourceSectors;

  constructor(private SectorService: SectorService) {
  }

  ngOnInit(): void {

    this.SectorService.getSectores().subscribe(
      (data: any) => {
        this.dataSourceSectors = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
