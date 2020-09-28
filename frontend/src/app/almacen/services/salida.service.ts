import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})
export class SalidaService {

  private path = ENDPOINTS;

  constructor(private http: HttpClient) { }

  getSalidas() {
    return this.http.get(this.path.SERVER.serve + this.path.SALIDA.GET);
  }

  getSalida(id){
    return this.http.get('http://localhost:8080/api/salida/' + id);
  }

  postSalida(salidaForm){
    return this.http.post<any>(this.path.SERVER.serve + this.path.SALIDA.POST, salidaForm.value);
  }

  updateSalida(id, salida){
    return this.http.put<any>('http://localhost:8080/api/salida/' + id, salida.value);
  }

  
}
