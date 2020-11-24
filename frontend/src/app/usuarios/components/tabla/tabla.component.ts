import {Component, OnInit} from '@angular/core';
import {UserService} from '../../../usuarios/services/user.service';
import { Router } from '@angular/router';
import { CoreService } from 'src/app/core/service/core.service';

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
  

  public dataSourceUsers;

  constructor(private UserService: UserService,
              private router: Router,
              private CoreService: CoreService) {
   }


  ngOnInit(): void {
    this.getUsuarios();
  }

  clickedRow(row){
    this.router.navigate(['main/usuarios/form/' +  row.id]);
  }

  getUsuarios(){
    this.UserService.getUsers().subscribe(
      (data: any)  => { 
        this.dataSourceUsers = this.CoreService.replaceFormat(data, ['cargo']);
      },
      (error) => {
        console.error(error);
      }

    );
  }


}
