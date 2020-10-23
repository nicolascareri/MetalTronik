import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})
export class RegistroService {

  private path = ENDPOINTS;

  constructor(private http: HttpClient) { }

  getRegistro(date) {
    console.log(date);
    return this.http.get(this.path.SERVER.serve + this.path.REGISTRO.GET + date);
  }


}
