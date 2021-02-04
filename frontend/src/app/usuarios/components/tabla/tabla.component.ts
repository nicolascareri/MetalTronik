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

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public columnsToDisplay: any[] = [
      {
        id: 1,
        property:'nombre',
        name: 'Nombre',
        sort: '',
        filterValue: '',
        width: '14%'
      },
      {
        id: 2,
        property:'apellido',
        name: 'Apellido',
        sort: '',
        filterValue: '',
        width: '15%'
      }, 
      {
        id: 3,
        property:'fnacimiento',
        name: 'Fecha de nacimiento',
        sort: '',
        filterValue: '',
        width: '15%'
      },
      {
        id: 4,
        property:'cargo',
        name: 'Cargo',
        sort: '',
        filterValue: '',
        width: '15%'
      }, 
      {
        id: 5,
        property:'legajo',
        name: 'Legajo',
        sort: '',
        filterValue: '',
        width: '15%'
      }, 
      {
        id: 6,
        property:'ciudad',
        name: 'Ciudad',
        sort: '',
        filterValue: '',
        width: '350px'
      },
      {
        id: 7,
        property:'pais',
        name: 'Pais',
        sort: '',
        filterValue: '',
        width: '20%'
      }, 
      {
        id: 8,
        property:'provincia',
        name: 'Provincia',
        sort: '',
        filterValue: '',
        width: '20%'
      }, 
      {
        id: 9,
        property:'calle',
        name: 'Calle',
        sort: '',
        filterValue: '',
        width: '20%'
      },
      {
        id: 10,
        property:'numero',
        name: 'Numero de calle',
        sort: '',
        filterValue: '',
        width: '20%'
      },
      {
        id: 11,
        property:'correo_electronico',
        name: 'Correo electronico',
        sort: '',
        filterValue: '',
        width: '20%'
      }
    ];
    public dataSourceUsers;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  constructor(private UserService: UserService,
              private router: Router,
              private CoreService: CoreService) {
   }


  ngOnInit(): void {
    this.getUsuarios();
  }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  clickedRow(row){
    this.router.navigate(['main/personal/form/' +  row.id]);
  }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  getUsuarios(){
    this.UserService.getUsers().subscribe(
      (data: any)  => { 
        this.dataSourceUsers = this.CoreService.replaceFormat(data, ['cargo', 'direccion']);
      },
      (error) => {
        console.error(error);
      }

    );
  }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
