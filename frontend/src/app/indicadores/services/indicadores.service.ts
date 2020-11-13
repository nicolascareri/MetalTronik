import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})
export class IndicadoresService {

  private path = ENDPOINTS;

  constructor(protected http: HttpClient) {
  }


  getUsuarios(){
    return this.http.get(this.path.SERVER.serve + this.path.INDICADORES.USUARIOS);
  }




}
