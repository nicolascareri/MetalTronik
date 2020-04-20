import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class OrdenestrabajoService {

  constructor(protected http: HttpClient) { }

  getAllOrdenes(){
    return this.http.get('http://localhost:8080/api/ordenes-trabajo');
  }

}
