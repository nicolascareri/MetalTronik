import { Component, OnInit, Input, SimpleChanges, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaComponent implements OnInit {

  public hasClickRowFunction: boolean;
  @Input() columns: any;
  @Input() ds: any = [{}];
  @Input() width = '100%';
  @Input() search = true;
  @Output() clickRow  = new EventEmitter();
  displayData: any;
  public errorMessage = '';
  constructor() { }

  ngOnInit(): void {
    this.displayData = this.ds;
    if (!this.ds || ! this.columns) {
      this.errorMessage = 'Error al instanciar la tabla. Revisar parámetros de input (ds o columns no esta siendo incluido)';
    }
  }

  recordSelected(row: any) {
    this.clickRow.emit(row);
  }  

  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    if ( changes.ds ) {
      this.ds = this.ds ? this.ds : [];
      this.displayData = this.ds;
    }
  }


}
