import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PrioridadesService {

  constructor(protected http: HttpClient) {
  }

  getPrioridades() {
    return this.http.get('http://localhost:8080/api/prioridades');
  }

  postPrioridad(form){
    return this.http.post<any>('http://localhost:8080/api/prioridades', form.value);
  }

}
