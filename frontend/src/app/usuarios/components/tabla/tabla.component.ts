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

    public columnsToDisplay: any[] = [
      {
        id: 1,
        property:'dni',
        name: 'DNI',
        sort: 'up',
        filterValue: '',
        width: '15%'
      }, 
      {
        id: 2,
        property:'nombre',
        name: 'Nombre',
        sort: '',
        filterValue: '',
        width: '14%'
      },
      {
        id: 3,
        property:'apellido',
        name: 'Apellido',
        sort: '',
        filterValue: '',
        width: '15%'
      }, 
      {
        id: 4,
        property:'fnacimiento',
        name: 'Fecha de nacimiento',
        sort: '',
        filterValue: '',
        width: '15%'
      },
      {
        id: 5,
        property:'cargo',
        name: 'Cargo',
        sort: '',
        filterValue: '',
        width: '15%'
      }, 
      {
        id: 6,
        property:'legajo',
        name: 'Legajo',
        sort: '',
        filterValue: '',
        width: '15%'
      }, 
      {
        id: 7,
        property:'nombre_usuario',
        name: 'Nombre de usuario del sistema',
        sort: '',
        filterValue: '',
        width: '15%'
      }, 
      {
        id: 8,
        property:'contrasenia',
        name: 'Contraseñia asociada',
        sort: '',
        filterValue: '',
        width: '350px'
      }, 
      {
        id: 9,
        property:'ciudad',
        name: 'Ciudad',
        sort: '',
        filterValue: '',
        width: '350px'
      },
      {
        id: 10,
        property:'pais',
        name: 'Pais',
        sort: '',
        filterValue: '',
        width: '20%'
      }, 
      {
        id: 11,
        property:'provincia',
        name: 'Provincia',
        sort: '',
        filterValue: '',
        width: '20%'
      }, 
      {
        id: 12,
        property:'codigo_postal',
        name: 'Codigo postal',
        sort: '',
        filterValue: '',
        width: '20%'
      },  
      {
        id: 13,
        property:'direccion',
        name: 'Direccion',
        sort: '',
        filterValue: '',
        width: '20%'
      },
      {
        id: 13,
        property:'correo_electronico',
        name: 'Correo electronico',
        sort: '',
        filterValue: '',
        width: '20%'
      }
    ];
  

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

    this.UserService.getUsers().subscribe(

      (data: any)  => { // Success
        this.dataSourceUsers = data;
      },
      (error) => {
        console.error(error);
      }

    );


  }


}
