import { Injectable } from '@angular/core';
import * as moment from 'moment';

@Injectable({
  providedIn: 'root'
})
export class CoreService {

  constructor() { }


  public dateServerFormat = 'DD-MM-YYYY HH:mm:ss';

  replaceFormat(data: Array<any>, formats: Array<string>) {
    return data.map((val: any) => {
      const result = val;
      formats.forEach(
        format => {
          switch (format) {
            case 'fechaEntrega':
              if (val.fechaEntrega && moment(val.fechaEntrega, this.dateServerFormat).isValid()) {
                result.fechaEntrega = this.getFormatDate(val.fechaEntrega);
              } else {
                result.fechaEntrega = '';
              }
              break;
            case 'fechaRealizar':
              if (val.fechaRealizar && moment(val.fechaRealizar, this.dateServerFormat).isValid()) {
                result.fechaRealizar = this.getFormatDate(val.fechaRealizar);
              } else {
                result.fechaRealizar = '';
              }
              break;
            case 'encargo': 
                if(result.encargo){
                  result.encargo = result.encargo.nombre;
                }
                break;
            case 'responsable':
              if(result.responsable){
                result.responsable = result.responsable.nombre;
              }
              break;
            case 'maquina':
              if(result.maquina){
                result.planta = result.maquina.planta.nombre;
                result.sector = result.maquina.sector.descripcion;
                result.maquina = result.maquina.maquina_cod;
              }  
              break;
            case 'prioridad':
              if(result.prioridad){
                result.prioridad = result.prioridad.nombre;
              }
              break;
            case 'tipo':
              if(result.tipo){
                result.tipo = result.tipo.nombre;
              }
          }
        }
      );
      return result;
    });
  }

  getFormatDate(date: string): string {
    moment.locale('es');
    const dateMoment = moment(date, this.dateServerFormat);
    const month = dateMoment.format('MMMM');
    const day = dateMoment.format('DD');
    const year = dateMoment.format('YYYY');
    return day + ' de ' + month[0].toUpperCase() + month.substr(1) + ' de ' + year;
  }

  isNumeric(str) {
    return !/[0-9,]/g.test(str);
  }

  getEmailValidPatter() {
    return  /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
  }

}

