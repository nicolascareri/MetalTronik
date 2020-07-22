import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TipoService {

  constructor(protected http: HttpClient) {
  }

  getTipos() {
    return this.http.get('http://localhost:8080/api/tipo');
  }

  postTipo(form){
    return this.http.post<any>('http://localhost:8080/api/tipo', form.value)
  }
}
