import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})
export class EntradaService {

  private path = ENDPOINTS;

  constructor(private http: HttpClient) { }

  getEntradas() {
    return this.http.get(this.path.SERVER.serve + this.path.ENTRADA.GET);
  }

  getEntrada(id){
    return this.http.get(this.path.SERVER.serve + this.path.ENTRADA.GETID + id);
  }

  postEntrada(entradaForm){
    return this.http.post<any>(this.path.SERVER.serve + this.path.ENTRADA.POST, entradaForm.value);
  }

  updateEntrada(id, entrada){
    return this.http.put<any>('http://localhost:8080/api/entrada/' + id, entrada.value);
  }
  
}
