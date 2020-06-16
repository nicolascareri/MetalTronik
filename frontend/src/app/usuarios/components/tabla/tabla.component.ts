import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup,  FormControl } from "@angular/forms";
import { UserService } from "../../../usuarios/services/user.service";

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaComponent implements OnInit {

  columnsToDisplay: any = ['id','nombre','apellido'];

  dataSourceUsers: any;

  form: FormGroup;

  createFormGroup(){
    return new FormGroup({
      id: new FormControl(''),
      nombre: new FormControl(''),
      apellido: new FormControl('')
    })
  }

  //Editar
  @Input() originalUser: any; 
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
