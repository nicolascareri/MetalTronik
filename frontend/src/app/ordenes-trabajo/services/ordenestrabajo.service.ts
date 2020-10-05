import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ENDPOINTS} from 'src/app/core/constants/constants';

@Injectable({
  providedIn: 'root'
})


export class OrdenestrabajoService {

  private path = ENDPOINTS;
  orders: any = this.getAllOrdenes();


  constructor(protected http: HttpClient) { }

  getAllOrdenes(){
    return this.http.get(this.path.SERVER.serve + this.path.ORDENES_TRABAJO.GET);
  }

  getOrder(id){
    return this.http.get(this.path.SERVER.serve + this.path.ORDENES_TRABAJO.GETID + id);
  }

  postOrder(orderForm){
    return this.http.post<any>(this.path.SERVER.serve + this.path.ORDENES_TRABAJO.POST, orderForm.value);
  }

  updateOrder(id, order){
    return this.http.put<any>(this.path.SERVER.serve + this.path.ORDENES_TRABAJO.PUT + id, order.value);
  }

}
