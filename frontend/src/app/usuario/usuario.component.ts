import { Component, OnInit } from '@angular/core';
import { UserService } from "../user.service";

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.scss']
})
export class UsuarioComponent implements OnInit {
  users: any = [];

  constructor(
    private userService: UserService, 
  ) { }

  ngOnInit(): void {
    this.userService.getUsers().subscribe(
      (data)  => { // Success
        this.users = data;
        console.log(data)
      },
      (error) => {
        console.error(error);
      }
    );
  }

}
