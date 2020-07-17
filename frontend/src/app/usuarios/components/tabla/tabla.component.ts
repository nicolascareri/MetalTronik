import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {UserService} from '../../../usuarios/services/user.service';

@Component({
  selector: 'app-tabla-usuario',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaUsuarioComponent implements OnInit {

  columnsToDisplay: any = ['id', 'dni', 'nombre', 'apellido', 'fnacimiento', 'cargo', 'legajo', 'nombre_usuario', 'contrasenia',
    'ciudad', 'pais', 'provincia', 'codigo_postal', 'direccion', 'correo_electronico'];

  dataSourceUsers: any;

  form: FormGroup;
  //Editar
  @Input() originalUser: any;

  createFormGroup() {
    return new FormGroup({
      id: new FormControl(''),
      dni: new FormControl(''),
      nombre: new FormControl(''),
      apellido: new FormControl(''),
      fnacimiento: new FormControl(''),
      cargo:  new FormControl(''),
      legajo: new FormControl(''),
      nombre_usuario: new FormControl(''),
      contrasenia: new FormControl(''),
      ciudad: new FormControl(''),
      pais: new FormControl(''),
      provincia: new FormControl(''),
      codigo_postal: new FormControl(''),
      direccion: new FormControl(''),
      correo_electronico: new FormControl('')
    })
  }
  @Output() close = new EventEmitter();



  constructor(private UserService: UserService) {
    this.form = this.createFormGroup();
   }

   applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSourceUsers.filter = filterValue.trim().toLowerCase();
    console.log(this.dataSourceUsers);
    console.log(this.dataSourceUsers.filter);

  }

  ngOnInit(): void {

    this.UserService.getUsers().subscribe(

      (data: any)  => { // Success
        this.dataSourceUsers = data;
        console.log(this.dataSourceUsers);
      },
      (error) => {
        console.error(error);
      }

    );


  }

//   openModal(ordenes){
//     let modal = document.getElementById("myModal");
//     modal.style.display = "block";
//     this.originalUser = ordenes;
//     console.log(this.originalUser);
//     this.setOriginalValues(this.originalUser);
//     console.log(this.form);


// }

}
