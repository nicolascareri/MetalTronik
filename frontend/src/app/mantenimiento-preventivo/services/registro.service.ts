import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})
export class RegistroService {

  private path = ENDPOINTS;

  constructor(private http: HttpClient) { }

  getRegistro(date) {
    return this.http.get(this.path.SERVER.serve + this.path.REGISTRO.GET + date);
  }

  getRegistroById(id) {
    return this.http.get(this.path.SERVER.serve + this.path.REGISTRO.GETID + id);
  }

  updateRegistro(id, tarea) {
    return this.http.put<any>(this.path.SERVER.serve + this.path.REGISTRO.PUT + id, tarea.value);
  }
  
  getSaves(date){
    return this.http.get(this.path.SERVER.serve + this.path.REGISTRO.GETSAVES + date);
  }

  saveCurrent(date){
    return this.http.post<any>(this.path.SERVER.serve + this.path.REGISTRO.POST, date);
  }




}
