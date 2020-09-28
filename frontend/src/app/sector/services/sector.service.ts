import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})
export class SectorService {

  constructor(protected http: HttpClient) {
  }

  private path = ENDPOINTS;

  getSectores() {
    return this.http.get(this.path.SERVER.serve + this.path.SECTORES.GET);
  }

  getSector(id){
    return this.http.get(this.path.SERVER.serve + this.path.SECTORES.GETID + id);
  }

  postSector(form){
    return this.http.post<any>(this.path.SERVER.serve + this.path.SECTORES.POST, form.value);
  }

  updateSector(id, sector){
    return this.http.put<any>(this.path.SERVER.serve + this.path.SECTORES.PUT + id, sector.value);
  }

}
