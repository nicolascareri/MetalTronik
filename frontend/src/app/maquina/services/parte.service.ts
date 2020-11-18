import { Injectable, Output } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})
export class ParteService {

  private path = ENDPOINTS;
  @Output() lasInsert : any = [];

  constructor(protected http: HttpClient) {
  }

  setLastInsert(part){
    this.lasInsert.push(part);
  }

  getLastInsert(){
    let parts : [] = this.lasInsert;
    return parts;
  }

  deleteLastInsert(part){
    var index = this.lasInsert.indexOf(part);
    if (index > -1) {
      this.lasInsert.splice(index, 1);  
    }
  }

  postParte(partsForm){
    return this.http.post<any>(this.path.SERVER.serve + this.path.PARTE.POST, partsForm.value);
  }

  deleteParte(id){
    return this.http.delete(this.path.SERVER.serve + this.path.PARTE.DELETE + id);
  }

  getParts(){
    return this.http.get(this.path.SERVER.serve + this.path.PARTE.GET);
  }

  getByMaquina(id){
    return this.http.get(this.path.SERVER.serve + this.path.PARTE.GETBYMAQUINA + id);
  }

  linkPart(id, parts){
    this.lasInsert = [];
    return this.http.put<any>(this.path.SERVER.serve + this.path.PARTE.PUT + id, parts);
  }

}
