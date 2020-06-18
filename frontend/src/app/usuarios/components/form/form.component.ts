import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { UserService } from "../../../usuarios/services/user.service";

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormComponent implements OnInit {

  createFormGroup(){
    return new FormGroup({
      nombre: new FormControl(''),
      apellido: new FormControl(''),
      dni: new FormControl(''),
      estado: new FormControl(30)
    })
  }

  userForm: FormGroup;


  constructor(private UserService: UserService) {
    this.userForm = this.createFormGroup();
   }

  ngOnInit(): void {
  }

  resetForm() {
    this.userForm.reset();
  }
  
  saveForm() {
    console.log(this.userForm.value);
    this.UserService.postUser(this.userForm).subscribe(
      user => alert("Se ha creado el usuario numero: " + user.id)
    );
    //this.router.navigate(['main/ordenes'])
  
    
  }

}
