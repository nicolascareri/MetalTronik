import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SectorService {

  constructor(protected http: HttpClient) { }

  getSectores() {
    return this.http.get('http://localhost:8080/api/sector');
  }

}
