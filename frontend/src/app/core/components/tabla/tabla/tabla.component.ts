import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaComponent implements OnInit {

  @Input() columnsToDisplay: any;

  @Input() ds: any;
  constructor() { }

  ngOnInit(): void {
  }

}
