import { Component, OnInit } from '@angular/core';
import { ChartType, ChartOptions } from 'chart.js';
import { Label } from 'ng2-charts';
import * as pluginDataLabels from 'chartjs-plugin-datalabels';
import { IndicadoresService } from "../../services/indicadores.service";

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.scss']
})
export class PieChartComponent implements OnInit {

  public pieChartOptions: ChartOptions = {
    responsive: true,
    legend: {
      position: 'left',
    },
    plugins: {
      datalabels: {
        formatter: (value, ctx) => {
          const label = ctx.chart.data.labels[ctx.dataIndex];
          return label;
        },
      },
    }
  };
  public pieChartLabels: Label[] = [];
  public pieChartLabelsPrioridades: Label[] = [];
  public pieChartData: number[] = [];
  public pieChartDataPrioridades: number[] = [];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartPlugins = [pluginDataLabels];
  public pieChartColors = [
    {
      backgroundColor: ['rgba(255,0,0,0.3)', 'rgba(0,255,0,0.3)', 'rgba(0,0,255,0.3)'],
    },
  ];
  public tipos: any = [];
  public cant: any = [];
  public dataSourceLabels: any = [];

  constructor(private IndicadoresService: IndicadoresService) { }

  ngOnInit(): void {
    this.getForTipo();
    this.getForPrioridad();
  }

  getForTipo(){
    this.IndicadoresService.getPieForTipos().subscribe(
      (data: any) => {
        this.pieChartLabels = data.labels;
        this.pieChartData = data.number;
        });
  }

  getForPrioridad(){
    this.IndicadoresService.getPieForPrioridades().subscribe(
      (data: any) => {
        this.pieChartLabelsPrioridades = data.labels;
        this.pieChartDataPrioridades = data.number;
      }
    );
  }

  public chartClicked({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

  public chartHovered({ event, active }: { event: MouseEvent, active: {}[] }): void {
    console.log(event, active);
  }

 }
