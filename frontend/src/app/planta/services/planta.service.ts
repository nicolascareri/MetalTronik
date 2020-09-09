import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlantaService {

  constructor(protected http: HttpClient) {
  }

  getPlanta(id){
    return this.http.get('http://localhost:8080/api/planta/' + id);
  }

  getPlantas() {
    return this.http.get('http://localhost:8080/api/planta');
  }

  postPlanta(formPlanta){
    return this.http.post<any>('http://localhost:8080/api/planta', formPlanta.value);
  }

  updatePlanta(id, planta){
    return this.http.put<any>('http://localhost:8080/api/planta/' + id, planta);
  }

}
