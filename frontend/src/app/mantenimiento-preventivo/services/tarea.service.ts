import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})
export class TareaService {

  private path = ENDPOINTS;

  constructor(private http: HttpClient) { }

  
  getTareas(){
    return this.http.get(this.path.SERVER.serve + this.path.TAREA.GET);
  }
  
  getTarea(id){
    return this.http.get(this.path.SERVER.serve + this.path.TAREA.GETID + id);
  }
  
  getHistorial(id){
    return this.http.get(this.path.SERVER.serve + this.path.TAREA.GETPUTHISTORIAL + id);
  }

  postTarea(tareaForm){
    return this.http.post<any>(this.path.SERVER.serve + this.path.TAREA.POST, tareaForm.value);
  }

  updateTarea(id, tarea){
    return this.http.put<any>(this.path.SERVER.serve + this.path.TAREA.PUT + id, tarea.value);
  }
}
