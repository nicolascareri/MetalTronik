import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})
export class PlantaService {

  private path = ENDPOINTS;

  constructor(protected http: HttpClient) {
  }

  getPlanta(id){
    return this.http.get(this.path.SERVER.serve + this.path.PLANTAS.GETID + id);
  }

  getPlantas() {
    return this.http.get(this.path.SERVER.serve + this.path.PLANTAS.GET);
  }

  postPlanta(formPlanta){
    return this.http.post<any>(this.path.SERVER.serve + this.path.PLANTAS.POST, formPlanta.value);
  }

  updatePlanta(id, planta){
    return this.http.put<any>(this.path.SERVER.serve + this.path.PLANTAS.PUT + id, planta.value);
  }

}
