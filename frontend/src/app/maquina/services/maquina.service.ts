import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

export class MaquinaService {

  constructor(protected http: HttpClient) {
  }

  getMaquina(id){
    return this.http.get('http://localhost:8080/api/maquina/' + id);
  }

  getMaquinas() {
    return this.http.get('http://localhost:8080/api/maquina');
  }


  postMaquina(machinesForm){
    return this.http.post<any>('http://localhost:8080/api/maquina', machinesForm.value);
  }

  getMaquinasSinRepuestos(){
    return this.http.get('http://localhost:8080/api/maquina/sinrepuesto');
  }

  updateMaquina(id, maquina){
    return this.http.put<any>('http://localhost:8080/api/maquina/' + id, maquina.value);
  }

  

}
