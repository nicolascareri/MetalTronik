import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {UserService} from '../../../usuarios/services/user.service';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-tabla-usuario',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaUsuarioComponent implements OnInit {

  columnsToDisplay: any = ['id', 'dni', 'nombre', 'apellido', 'fnacimiento', 'cargo', 'legajo', 'nombre_usuario', 'contrasenia',
    'ciudad', 'pais', 'provincia', 'codigo_postal', 'direccion', 'correo_electronico'];

  dataSourceUsers = new MatTableDataSource();

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

  applyFilter(filterValue: String) {
    this.dataSourceUsers.filter = filterValue.trim().toLowerCase();
  }

  nestedFilterCheck(search, data, key) {
    if (typeof data[key] === 'object') {
      for (const k in data[key]) {
        if (data[key][k] !== null) {
          search = this.nestedFilterCheck(search, data[key], k);
        }
      }
    } else {
      search += data[key];
    }
    return search;
  }

  filtro(){
    this.dataSourceUsers.filterPredicate = (data, filter: string)  => {
      const accumulator = (currentTerm, key) => {
        return this.nestedFilterCheck(currentTerm, data, key);
      };
      const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
      // Transform the filter by converting it to lowercase and removing whitespace.
      const transformedFilter = filter.trim().toLowerCase();
      return dataStr.indexOf(transformedFilter) !== -1;
    };
  }

  ngOnInit(): void {

    this.filtro();

    this.UserService.getUsers().subscribe(

      (data: any)  => { // Success
        this.dataSourceUsers.data = data;
      },
      (error) => {
        console.error(error);
      }

    );


  }


}
