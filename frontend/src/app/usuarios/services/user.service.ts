import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { ENDPOINTS } from "../../core/constants/constants";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private path = ENDPOINTS;


  constructor(protected http: HttpClient) {
  }

  getUsers() {
    return this.http.get(this.path.SERVER.serve + this.path.PERSONAL.GET);
  }

  getUser(id){
    return this.http.get(this.path.SERVER.serve + this.path.PERSONAL.GETID + id);
  }

  postUser(request){
    return this.http.post<any>(this.path.SERVER.serve + this.path.PERSONAL.POST, request);
  }

  updateUser(id, request){
    return this.http.put<any>(this.path.SERVER.serve + this.path.PERSONAL.PUT + id, request);
  }

  getCargos(){
    return this.http.get(this.path.SERVER.serve + this.path.CARGO.GET);
  }

  postCargo(form){
    return this.http.post<any>(this.path.SERVER.serve + this.path.CARGO.POST, form.value);
  }


}


