import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { ENDPOINTS } from "../../core/constants/constants";
import { MessageService } from "../../core/service/message.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public path = ENDPOINTS;
  public token;
  public userName;
  public messageTitleSuccess: any = " ";
  public messageTitleError: any = "ERROR!";
  public messageBody: any = "Autorización aceptada";

  constructor(private http: HttpClient,
              private router: Router,
              private MessageService: MessageService) { }

  

            

  setUserName(name){
    this.userName = name;
  }

  getUserName(){
    return this.userName;
  }

  showSuccess() {
    this.MessageService.showSuccess({
    title: this.messageTitleSuccess,
    body: this.messageBody
    });
  }
            
  showError(message) {
    this.MessageService.showError({
    title: this.messageTitleError,
    body: message
    })
  }

  login(loginForm) {
    this.http.post(this.path.SERVER.serve + this.path.USUARIOS.LOGIN, loginForm.value)
    .subscribe((data: any) => {
      
      localStorage.setItem('token', data.token);
      if(this.logIn()){ 
        this.showSuccess();       
        this.router.navigate(['main']);
      }else{
        this.router.navigate(['login']);
      }

    },
    error => this.showError(error.error.message)
    
    );
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['login']);
  }
 
  public logIn(): boolean {
    return (localStorage.getItem('token') !== null);
  }

}

