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
    return this.http.get(this.path.SERVER.serve + this.path.USUARIOS.GET);
  }

  getUser(id){
    return this.http.get(this.path.SERVER.serve + this.path.USUARIOS.GETID + id);
  }

  postUser(userForm){
    return this.http.post<any>(this.path.SERVER.serve + this.path.USUARIOS.POST, userForm.value);
  }

  updateUser(id, user){
    return this.http.put<any>(this.path.SERVER.serve + this.path.USUARIOS.PUT + id, user.value);
  }

  getCargos(){
    return this.http.get(this.path.SERVER.serve + this.path.CARGO.GET);
  }


}


