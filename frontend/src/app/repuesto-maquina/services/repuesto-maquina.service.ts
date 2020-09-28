import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})
export class RepuestoMaquinaService {

  private path = ENDPOINTS;

  constructor(protected http: HttpClient) { }


  getRepuestos(){
    return this.http.get(this.path.SERVER.serve + this.path.REPUESTOS.GET);
  }

  getRepuestoMaquina(){
    return this.http.get(this.path.SERVER.serve + this.path.REPUESTOMAQUINA.GET);
  }

  asociarRepuestos(id, repuestos:[]){
    return this.http.put<any>(this.path.SERVER.serve + 'api/repuesto-maquina/maquina/' + id + '/vincular' , repuestos);
  }
                            
}
