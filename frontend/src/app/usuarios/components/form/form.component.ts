import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {UserService} from '../../../usuarios/services/user.service';

@Component({
  selector: 'app-form-usuario',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss']
})
export class FormUsuarioComponent implements OnInit {

  createFormGroup() {
    return new FormGroup({
      nombre: new FormControl(''),
      apellido: new FormControl(''),
      dni: new FormControl(''),
      fnacimiento: new FormControl(''),
      cargo: new FormControl(''),
      legajo: new FormControl(''),
      nombre_usuario: new FormControl(''),
      contrasenia: new FormControl(''),
      ciudad: new FormControl(''),
      pais: new FormControl(''),
      provincia: new FormControl(''),
      codigo_postal: new FormControl(''),
      direccion: new FormControl(''),
      correo_electronico: new FormControl(''),
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
    this.UserService.postUser(this.userForm).subscribe(
      user => alert("Se ha creado el usuario numero: " + user.id)
    );
    //this.router.navigate(['main/ordenes'])


  }

}
