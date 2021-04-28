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

  getAsocById(id){
    return this.http.get(this.path.SERVER.serve + this.path.REPUESTOMAQUINA.GETID + id);
  }

  getRepuestoMaquina(){
    return this.http.get(this.path.SERVER.serve + this.path.REPUESTOMAQUINA.GET);
  }

  asociarRepuestos(request){
    return this.http.post<any>(this.path.SERVER.serve + this.path.REPUESTOMAQUINA.POST, request);
  }

  getMaquinasSinAsoc(){
    return this.http.get(this.path.SERVER.serve + this.path.REPUESTOMAQUINA.GETSINASOC);
  }

  updateAsociacion(id, request){
    return this.http.put<any>(this.path.SERVER.serve + this.path.REPUESTOMAQUINA.PUT + id, request);
  }
                            
}
