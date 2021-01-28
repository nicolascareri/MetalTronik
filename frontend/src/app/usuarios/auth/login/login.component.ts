import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

 public loginForm : FormGroup = new FormGroup({
  nombre_usuario: new FormControl(''),
  password: new FormControl(''),
  });
   
  constructor(private authService: AuthService) {  }
  
  ngOnInit(): void {
  }
  
  login() {
  this.authService.login(this.loginForm);
  }

}
