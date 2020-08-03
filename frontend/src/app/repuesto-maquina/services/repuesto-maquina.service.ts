import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RepuestoMaquinaService {

  constructor(protected http: HttpClient) { }


  getRepuestosById(id){
    return this.http.get('http://localhost:8080/api/repuesto-maquina/maquina/' + id);
  }

  getRepuestos(){
    return this.http.get('http://localhost:8080/api/repuesto-maquina');
  }

  asociarRepuestos(id, repuestos:[]){
    return this.http.put<any>('htpp://localhost:8080/repuesto-maquina/maquina/' + id + "/" , repuestos);
  }
}
