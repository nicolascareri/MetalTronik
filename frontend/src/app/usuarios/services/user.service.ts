import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(protected http: HttpClient) {
  }

  getUsers() {
    return this.http.get('http://localhost:8080/api/usuario');
  }

  getUser(id){
    return this.http.get('http://localhost:8080/api/usuario' + id);
  }

  postUser(userForm){
    return this.http.post<any>('http://localhost:8080/api/usuario', userForm.value);
  }

  updateUser(id, user){
    return this.http.put<any>('http://localhost:8080/api/usuario' + id, user);
  }


}


