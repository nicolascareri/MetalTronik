import { Component, OnInit } from '@angular/core';
import { UserService } from "../../../usuarios/services/user.service";
import {FormControl, FormGroup} from '@angular/forms';



@Component({
  selector: 'app-cargos',
  templateUrl: './cargos.component.html',
  styleUrls: ['./cargos.component.scss']
})
export class CargosComponent implements OnInit {

  public panelOpenState = false;
  public dataSourceCargos: any;
  public form: FormGroup;

  constructor(private UserService: UserService) { }

  ngOnInit(): void {
    this.getCargos();
    this.form = new FormGroup({
      nombre_cargo: new FormControl('')
    });
  }

  getCargos(){
    this.UserService.getCargos().subscribe(
      (data: any) => {
        this.dataSourceCargos = data;
      },
      (error) => {
        console.log(error.error);
      }
    );
  }

  addCargo(){
    this.UserService.postCargo(this.form).subscribe(
      data => {
        console.log(data);
        location.reload();
        
      },
      (error) => {
        console.log(error.error);
        
      }
    );
  }

}
