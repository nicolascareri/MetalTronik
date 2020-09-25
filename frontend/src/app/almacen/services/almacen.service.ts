import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class AlmacenService {

  constructor(protected http: HttpClient) { }

  getRepuestos() {
    return this.http.get('http://localhost:8080/api/repuesto');
  }

  getRepuesto(id){
    return this.http.get('http://localhost:8080/api/repuesto/' + id);
  }

  postRepuesto(repuestForm){
    return this.http.post<any>('http://localhost:8080/api/repuesto', repuestForm.value);
  }

  updateRepuesto(id, repuesto){
    return this.http.put<any>('http://localhost:8080/api/repuesto/' + id, repuesto.value);
  }



}
