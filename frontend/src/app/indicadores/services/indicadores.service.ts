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


  getFormForUsers(){
    return this.http.get(this.path.SERVER.serve + this.path.INDICADORES.USUARIOS);
  }

  getFormForSectors(){
    return this.http.get(this.path.SERVER.serve + this.path.INDICADORES.SECTORES);
  }

  getPieForTipos(){
    return this.http.get(this.path.SERVER.serve + this.path.INDICADORES.PIE_TIPOS);
  }

  getPieForPrioridades(){
    return this.http.get(this.path.SERVER.serve + this.path.INDICADORES.PIE_PRIORIDADES);
  }




}
