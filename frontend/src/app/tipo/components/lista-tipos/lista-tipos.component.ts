import { Component, Input, OnInit } from '@angular/core';
import { TipoService } from "../../services/tipo.service";
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-lista-tipos',
  templateUrl: './lista-tipos.component.html',
  styleUrls: ['./lista-tipos.component.scss']
})
export class ListaTiposComponent implements OnInit {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

  @Input() tipo: String = '';
  @Input() title: String= '';
  public form: FormGroup;
  public panelOpenState = false;
  public dataSourceTipos: any;
  
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  constructor(private TipoService: TipoService) 
  {}

  ngOnInit(): void {
    this.getTipos(this.tipo);
    this.form = new FormGroup({
      nombre: new FormControl(''),
      tipo: new FormControl(this.tipo)
    });
  }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  addTipo() {  
    this.TipoService.postTipo(this.form).subscribe(
      prioridad =>  
      location.reload()  
    );
  }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  getTipos(tipo: any){
    this.TipoService.getTipos(tipo).subscribe(
      (data: any) => {
        this.dataSourceTipos = data;
      },
      (error) => {
        console.log(error.error);
      }
    );
  }

}
