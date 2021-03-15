import { Injectable } from '@angular/core';
import * as moment from 'moment';

@Injectable({
  providedIn: 'root'
})
export class CoreService {

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  public dateServerFormat = 'YYYY-MM-DD HH:mm:ss';

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  constructor() { }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  replaceFormat(data: Array<any>, formats: Array<string>) {
    return data.map((val: any) => {
      const result = val;
      formats.forEach(
        format => {
          switch (format) {
            case 'fechainicio':
              if (result.fechainicio && moment(result.fechainicio, this.dateServerFormat).isValid()) {
                result.fechainicio = this.getFormatDate(result.fechainicio);
              } else {
                result.fechainicio = '';
              }
              break;
              case 'fechaFin':
                if (result.fechaFin && moment(result.fechaFin, this.dateServerFormat).isValid()) {
                  result.fechaFin = this.getFormatDate(result.fechaFin);
                } else {
                  result.fechaFin = '';
                }
                break;
            case 'fechaEntrega':
              if (result.fechaEntrega && moment(result.fechaEntrega, this.dateServerFormat).isValid()) {
                result.fechaEntrega = this.getFormatDate(result.fechaEntrega);
              } else {
                result.fechaEntrega = '';
              }
              break;
            case 'fechaRealizar':
              if (result.fechaRealizar && moment(result.fechaRealizar, this.dateServerFormat).isValid()) {
                result.fechaRealizar = this.getFormatDate(result.fechaRealizar);
              } else {
                result.fechaRealizar = '';
              }
              break;
            case 'fechaRealizada':
              console.log(result);
              
              if (result.fechaRealizada && moment(result.fechaRealizada, this.dateServerFormat).isValid()) {
                  result.fechaRealizada = this.getFormatDate(result.fechaRealizada);
              } else {
                  result.fechaRealizada = '';
              }
              break;
            case 'fecha':
              if (result.fecha && moment(result.fecha, this.dateServerFormat).isValid()) {
                result.fecha = this.getFormatDate(result.fecha);
              } else {
                result.fecha = '';
              }
              break;
            case 'fechaPlanificada':
              if (result.fechaPlanificada && moment(result.fechaPlanificada, this.dateServerFormat).isValid()) {
                result.fechaPlanificada = this.getFormatDate(result.fechaPlanificada);
              } else {
                result.fechaPlanificada = '';
              }
              break;
            case 'fecha_correccion':
              if (result.fecha_correccion && moment(result.fecha_correccion, this.dateServerFormat).isValid()) {
                result.fecha_correccion = this.getFormatDate(result.fecha_correccion);
              } else {
                result.fecha_correccion = '';
              }
              break;
            case 'inicio':
              if (result.inicio && moment(result.inicio, this.dateServerFormat).isValid()) {
                console.log(result);
                
                result.inicio = this.getFormatDate(result.inicio);
              } else {
                result.inicio = '';
              }
              break;  
            case 'encargo': 
                if(result.encargo){
                  result.encargo = result.encargo.nombre + " " + result.encargo.apellido;
                }
                break;
            case 'encargado': 
                if(result.encargado){
                  result.encargado = result.encargado.nombre + " " + result.encargado.apellido;
                }
                break;
            case 'solicitante': 
                if(result.solicitante){
                  result.solicitante = result.solicitante.nombre + " " + result.solicitante.apellido;
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
                result.sector = result.maquina.sector.nombre;
                result.maquina = result.maquina.maquina_cod;
              }  
              break;
            case 'parte':
              if(result.parte){
                result.parte = result.parte.codigo;
                
              }  
              break;
            case 'parteOrden':
              if(result.ordenTrabajo.parte){
                result.parte = result.ordenTrabajo.parte.codigo;
              }  
                break;
            case 'tarea':
              if(result.tarea){
                result.sector = result.tarea.maquina.sector.descripcion;
                result.maquina = result.tarea.maquina.maquina_cod;
                result.tareaNombre = result.tarea.tarea;
                if(result.realizo == true){
                  result.realizo = result.realizo = "Si"
                }else{
                  result.realizo = result.realizo = "No"
                } 
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
                result.ordentrabajo_id = result.ordenTrabajo.ordentrabajo_id;
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
                    result.sector = result.sector.nombre;
                  }
                  break;
            case 'codigoProducto':
                  if(result.repuesto){
                    result.codigoProducto = result.repuesto.codigoProducto;
                  }
                  break;
            case 'modelo':
                  if(result.repuesto){
                    result.modelo = result.repuesto.modelo;
                  }
                  break;
            case 'marca':
                  if(result.repuesto){
                    result.marca = result.repuesto.marca;
                  }
                  break;
            case 'nombre':
                  if(result.repuesto){
                    result.nombre = result.repuesto.nombre;
                  }
                  break;
            case 'tipoRepuesto':
                  if(result.tipo_repuesto){
                    result.nombre = result.tipo_repuesto.nombre;
                  }
                  break;
            case 'tipoExistencia':
                  if(result.tipo_repuesto){
                    result.tipo_repuesto = result.tipo_repuesto.nombre;
                  }
                  break;
            case 'ubicacion':
                  if(result.repuesto){
                    result.ubicacion = result.repuesto.ubicacion;
                  }
                  break;
            case 'unidad':
                  if(result.repuesto){
                    result.unidad = result.repuesto.unidad;
                  }
                  break;
            case 'puntoPedido':
                  if(result.repuesto){
                    result.puntoPedido = result.repuesto.puntoPedido;
                  }
                  break;
            case 'stockObjetivo':
                  if(result.repuesto){
                    result.stockObjetivo = result.repuesto.stockObjetivo;
                  }
                  break;
            case 'existencia':
                  if(result.repuesto){
                    result.existencia = result.repuesto.existencia;
                  }
                  break;
            case 'cantidad_instalada':
                  if(result){
                    result.cantidad_instalada = result.cantidad_instalada;
                  }
                  break;
            case 'cargo':
                  if(result.cargo){
                    result.cargo = result.cargo.nombre;
                  }
                  break;
            case 'direccion':
                  if(result.direccion){
                    result.calle = result.direccion.calle;
                    result.numero = result.direccion.numero;
                    result.ciudad = result.direccion.ciudad;
                    result.pais = result.direccion.pais;
                    result.provincia = result.direccion.provincia;
                  }
                  break;
            case 'estado':
              if(result.estado){
                result.estado === 'PENDIENTE'? result.estado = 'PENDIENTE': result.estado = 'OK';
              }
          }
        }
      );
      return result;
    });
  }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}