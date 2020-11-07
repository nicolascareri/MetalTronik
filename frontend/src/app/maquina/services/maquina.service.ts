import {Injectable, Output} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})

export class MaquinaService {

  private path = ENDPOINTS;
  @Output() lastId : any;

  constructor(protected http: HttpClient) {
  }

  setLastId(id){
    this.lastId = id;
  }

  getLastId(){
    return this.lastId;
  }

  getMaquina(id){
    return this.http.get(this.path.SERVER.serve + this.path.MAQUINAS.GETID + id);
  }

  getMaquinas() {
    return this.http.get(this.path.SERVER.serve + this.path.MAQUINAS.GET);
  }


  postMaquina(machinesForm){
    return this.http.post<any>(this.path.SERVER.serve + this.path.MAQUINAS.POST, machinesForm.value);
  }


  updateMaquina(id, maquina){
    return this.http.put<any>(this.path.SERVER.serve + this.path.MAQUINAS.PUT + id, maquina.value);
  }


  

}
