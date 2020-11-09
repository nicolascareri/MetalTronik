import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})
export class ParteService {

  private path = ENDPOINTS;

  constructor(protected http: HttpClient) {
  }

  postParte(partsForm){
    return this.http.post<any>(this.path.SERVER.serve + this.path.PARTE.POST, partsForm.value);
  }
}
