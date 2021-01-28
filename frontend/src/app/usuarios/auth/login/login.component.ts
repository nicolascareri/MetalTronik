import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
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

  login() {
  console.log("you are logging in");
  this.authService.login(this.loginForm);
  }

  ngOnInit(): void {
  }

  

}
