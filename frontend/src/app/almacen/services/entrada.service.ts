import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EntradaService {

  constructor(private http: HttpClient) { }

  getEntradas() {
    return this.http.get('http://localhost:8080/api/entrada');
  }

  getEntrada(id){
    return this.http.get('http://localhost:8080/api/entrada/' + id);
  }

  postEntrada(entradaForm){
    return this.http.post<any>('http://localhost:8080/api/entrada', entradaForm.value);
  }

  updateEntrada(id, entrada){
    return this.http.put<any>('http://localhost:8080/api/entrada/' + id, entrada.value);
  }
  
}
