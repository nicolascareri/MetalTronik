import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MaquinaService {

  constructor(protected http: HttpClient) { }

  getMaquinas() {
    return this.http.get('http://localhost:8080/api/maquina');
  }

  
  postMaquina(machinesForm){
    return this.http.post<any>('http://localhost:8080/api/maquina', machinesForm.value);
  }

}
