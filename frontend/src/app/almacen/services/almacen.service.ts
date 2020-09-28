import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})

export class AlmacenService {

  private path = ENDPOINTS;

  constructor(protected http: HttpClient) { }

  getRepuestos() {
    return this.http.get(this.path.SERVER.serve + this.path.REPUESTOS.GET);
  }

  getRepuesto(id){
    return this.http.get(this.path.SERVER.serve + this.path.REPUESTOS.GETID + id);
  }

  postRepuesto(repuestForm){
    return this.http.post<any>(this.path.SERVER.serve + this.path.REPUESTOS.POST, repuestForm.value);
  }

  updateRepuesto(id, repuesto){
    return this.http.put<any>(this.path.SERVER.serve + this.path.REPUESTOS.PUT + id, repuesto.value);
  }



}
