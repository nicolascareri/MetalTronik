import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  showNote(){
    this.toastrService.success('capa', 'tittle');
  }

  constructor(private toastrService: ToastrService) { }

  ngOnInit(): void {
  }

}
