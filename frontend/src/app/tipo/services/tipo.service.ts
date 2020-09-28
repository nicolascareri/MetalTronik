import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})
export class TipoService {

  private path = ENDPOINTS;

  constructor(protected http: HttpClient) {
  }


  getTipos() {
    return this.http.get(this.path.SERVER.serve + this.path.TIPOS.GET);
  }

  postTipo(form){
    return this.http.post<any>(this.path.SERVER.serve + this.path.TIPOS.POST ,form.value)
  }
}
