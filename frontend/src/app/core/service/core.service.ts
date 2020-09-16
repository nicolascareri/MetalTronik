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
                  result.encargo = result.encargo.nombre + " " + result.encargo.apellido;
                }
                break;
            case 'encargo1': 
                if(result.encargo1){
                  result.encargo1 = result.encargo1.nombre + " " + result.encargo1.apellido;
                }
                break;
            case 'encargo2': 
                if(result.encargo2){
                  result.encargo2 = result.encargo2.nombre + " " + result.encargo2.apellido;
                }
                break;
            case 'encargo3': 
                if(result.encargo3){
                  result.encargo3 = result.encargo3.nombre + " " + result.encargo3.apellido;
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
                if(result.tipo.nombre){
                  result.tipo = result.tipo.nombre;
                }
                break;
            case 'ordentrabajo':
              if(result.ordenTrabajo){
                result.ordentrabajo_cod = result.ordenTrabajo.ordentrabajo_cod;
                result.tipo = result.ordenTrabajo.tipo.nombre;
              }
              break;
            case 'planta':
                if(result.planta){
                  result.planta = result.planta.nombre;
                }
                break;
            case 'sector':
                  if(result.sector){
                    result.sector = result.sector.descripcion;
                  }
                  break;
            case 'nombreRepuesto':
                  if(result.repuesto){
                    result.repuesto = result.repuesto.nombre;
                  }
                  break;
            case 'estado':
              if(result.estado){
                result.estado === 1? result.estado = 'Pendiente': result.estado = 'Ok';
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

  getPager(totalItems: number, currentPage: number = 1, pageSize: number = 10, data: any) {

    const totalPages = Math.ceil(totalItems / pageSize);
    let startPage: number;
    let endPage: number;

    if (totalPages <= 5) {
        startPage = 1;
        endPage = totalPages;
    } else {
        if (currentPage <= 3) {
            startPage = 1;
            endPage = 5;
        } else if (currentPage + 1 >= totalPages) {
            startPage = totalPages - 4;
            endPage = totalPages;
        } else {
            startPage = currentPage - 2;
            endPage = currentPage + 2;
        }
    }
  }
}