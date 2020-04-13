import { Component, OnInit } from '@angular/core';
import { UserService } from "../user.service";
import {MatTableDataSource} from '@angular/material/table';

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})



export class TablaComponent implements OnInit {

  
  users: any = [];
  columnsToDisplay: any = ['id', 'nombre', 'apellido', 'dni'];
  dataSource: any ;
  constructor( private userService: UserService,) {}

  
  

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    console.log(this.dataSource.filter);
    
  }


  ngOnInit(): void {

    

    this.userService.getUsers().subscribe(

      (data: any)  => { // Success
        this.users = data;
        this.dataSource = new MatTableDataSource(this.users);
      },
      (error) => {
        console.error(error);
      }

    );

    
  }

}
