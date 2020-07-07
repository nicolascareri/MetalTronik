import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MantenimientoCorrectivoService {

  constructor(protected http: HttpClient) { }

  getMantenimientosCorrectivos(){
    return this.http.get('http://localhost:8080/api/mantenimiento-correctivo');
  }

  postMantenimientoCorrectivo(mantenimientoCorrectivoForm){
    return this.http.post<any>('http://localhost:8080/api/mantenimiento-correctivo', mantenimientoCorrectivoForm.value);
  }
}
