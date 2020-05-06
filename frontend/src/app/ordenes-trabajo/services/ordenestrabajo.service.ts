import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ENDPOINTS } from 'src/app/core/constants/constants';

@Injectable({
  providedIn: 'root'
})


export class OrdenestrabajoService {

  private url = ENDPOINTS;

  constructor(protected http: HttpClient) { }

  getAllOrdenes(){
    return this.http.get('http://localhost:8080/' + this.url.ORDENES_TRABAJO.GET);
  }

  postOrder(orderForm){
    return this.http.post<any>('http://localhost:8080/' + this.url.ORDENES_TRABAJO.POST, orderForm.value);
  }

}
