import { Component, Input, OnInit } from '@angular/core';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import * as pluginDataLabels from 'chartjs-plugin-datalabels';
import { Label } from 'ng2-charts';
import { IndicadoresService } from "../services/indicadores.service";

@Component({
  selector: 'app-indicadores',
  templateUrl: './indicadores.component.html',
  styleUrls: ['./indicadores.component.scss']
})
export class IndicadoresComponent implements OnInit {

  public barChartOptions: ChartOptions = {
    responsive: true,
    // We use these empty structures as placeholders for dynamic theming.
    scales: { xAxes: [{}], yAxes: [{}] },
    plugins: {
      datalabels: {
        anchor: 'end',
        align: 'end',
      }
    }
  };
  @Input() barChartLabels: Label[] = ['Sector'];
  @Input() barChartType: ChartType = 'bar';
  @Input() barChartLegend = true;
  @Input() barChartPlugins = [pluginDataLabels];


  public barChartData: ChartDataSets[] = [

    { data: [65, 59, 80, 81, 56, 55, 40], 
      label: 'Usuario 1' 
    }
];

  constructor(private IndicadoresService: IndicadoresService) { }

  ngOnInit(): void {
    this.getResults();
  }

  public getResults(){
    this.IndicadoresService.getUsuarios().subscribe(
      results => {
        
        this.barChartData.push(results);

      },
      error => {
        console.log(error.error);
        
      }
    );
  }

  // events
  public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public randomize(): void {
    // Only Change 3 values
    this.barChartData[0].data = [
      Math.round(Math.random() * 100),
      59,
      80,
      (Math.random() * 100),
      56,
      (Math.random() * 100),
      40 ];
  }

}
