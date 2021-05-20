import { Injectable } from '@angular/core';
import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';
const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet; charset=UTF-8'
const EXCEL_EXT = '.xlsx';
@Injectable()

export class ExporterService {

  constructor() { }

  exportToExcel(json:any[], excelFileName: string): void{
    const worksheet : XLSX.WorkSheet = XLSX.utils.json_to_sheet(json);
    const workbook : XLSX.WorkBook = { 
      Sheets: {'data': worksheet},
      SheetNames: ['data']
    };
    const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array'});
    this.saveAsExcel(excelBuffer, excelFileName);
  }

  private saveAsExcel(buffer: any, filename:string): void{
    const data: Blob = new Blob([buffer], {type: EXCEL_TYPE});
    FileSaver.saveAs(data, filename + '_export_' + new Date().getTime() + EXCEL_EXT);
  }
}


