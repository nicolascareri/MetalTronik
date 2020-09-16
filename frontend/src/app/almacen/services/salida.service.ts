import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SalidaService {

  constructor(private http: HttpClient) { }

  getSalidas() {
    return this.http.get('http://localhost:8080/api/salida');
  }

  getSalida(id){
    return this.http.get('http://localhost:8080/api/salida/' + id);
  }

  postSalida(salidaForm){
    return this.http.post<any>('http://localhost:8080/api/salida', salidaForm.value);
  }

  updateSalida(id, salida){
    return this.http.put<any>('http://localhost:8080/api/salida/' + id, salida.value);
  }

  
}
