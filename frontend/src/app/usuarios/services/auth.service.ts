import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { ENDPOINTS } from "../../core/constants/constants";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public path = ENDPOINTS;
  public token;

  constructor(private http: HttpClient,private router: Router) { }

  login(loginForm) {
    this.http.post(this.path.SERVER.serve + this.path.USUARIOS.LOGIN, loginForm.value)
    .subscribe((data: any) => {
      
      localStorage.setItem('token', data.token);
      if(this.logIn()){
        this.router.navigate(['main']);
      }else{
        this.router.navigate(['login']);
      }
      console.log(data.token);
    })
  }

  logout() {
    localStorage.removeItem('token');
  }
 
  public logIn(): boolean {
    return (localStorage.getItem('token') !== null);
  }

}

