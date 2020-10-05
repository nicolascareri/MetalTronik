import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})
export class PrioridadesService {

  private path = ENDPOINTS;

  constructor(protected http: HttpClient) {
  }

  
  getPrioridades() {
    return this.http.get(this.path.SERVER.serve + this.path.PRIORIDADES.GET);
  }

  postPrioridad(form){
    return this.http.post<any>(this.path.SERVER.serve + this.path.PRIORIDADES.POST, form.value);
  }

}
