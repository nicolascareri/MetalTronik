import { Component, Input, OnInit } from '@angular/core';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import * as pluginDataLabels from 'chartjs-plugin-datalabels';
import { Label } from 'ng2-charts';
import { IndicadoresService } from "../../services/indicadores.service";

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
  @Input() barChartLabelUsers: Label[] = ['Usuarios'];
  @Input() barChartLabelSectors: Label[] = ['Sectores'];
  @Input() barChartType: ChartType = 'bar';
  @Input() barChartLegend = true;
  @Input() barChartPlugins = [pluginDataLabels];

  public dataSourceFormula;


  public barChartDataUsers: ChartDataSets[] = [];
  public barChartDataSectors: ChartDataSets[] = [];

  constructor(private IndicadoresService: IndicadoresService) { }

  ngOnInit(): void {
    this.getFormIUsers();
    this.getFormISectors();
  }

  public getFormIUsers(){
    this.IndicadoresService.getFormForUsers().subscribe(
      (data: any) => {
        this.barChartDataUsers = data;
      },
      error => {
        console.log(error.error);
      }
    );
  }

  public getFormISectors(){
    this.IndicadoresService.getFormForSectors().subscribe(
      (data: any) => {
        console.log(data);
        
        this.barChartDataSectors = data;
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



}
