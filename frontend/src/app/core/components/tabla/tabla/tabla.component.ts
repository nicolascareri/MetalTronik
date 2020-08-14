import { Component, OnInit, Input, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaComponent implements OnInit {

  @Input() columns: any;
  @Input() ds: any = [{}];
  displayData: any;

  constructor() { }

  ngOnInit(): void {
  }
  
  ngOnChanges(changes: SimpleChanges): void {
    console.log(changes);
    
    if ( changes.ds ) {
      this.ds = this.ds ? this.ds : [];
      this.displayData = this.ds;
    }
  }


}
