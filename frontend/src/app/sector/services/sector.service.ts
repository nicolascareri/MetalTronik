import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SectorService {

  constructor(protected http: HttpClient) {
  }

  getSectores() {
    return this.http.get('http://localhost:8080/api/sector');
  }

  getSector(id){
    return this.http.get('http://localhost:8080/api/sector/' + id);
  }

  postSector(form){
    return this.http.post<any>('http://localhost:8080/api/sector', form.value);
  }

  updateSector(id, sector){
    return this.http.put<any>('http://localhost:8080/api/sector/' + id, sector);
  }

}
