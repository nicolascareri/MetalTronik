import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";


@Injectable({
  providedIn: 'root'
})
export class MantenimientoCorrectivoService {

  private path = ENDPOINTS;

  constructor(protected http: HttpClient) {
  }

  getMantenimiento(id){
    return this.http.get(this.path.SERVER.serve + this.path.MANTENIMIENTOCORRECTIVO.GETID + id);
  }

  getMantenimientosCorrectivos() {
    return this.http.get(this.path.SERVER.serve + this.path.MANTENIMIENTOCORRECTIVO.GET);
  }

  postMantenimientoCorrectivo(mantenimientoCorrectivoForm){
    return this.http.post<any>(this.path.SERVER.serve + this.path.MANTENIMIENTOCORRECTIVO.POST, mantenimientoCorrectivoForm.value);
  }

  update(id, mantenimiento){
    return this.http.put<any>(this.path.SERVER.serve + this.path.MANTENIMIENTOCORRECTIVO.PUT + id, mantenimiento.value);
  }
}
