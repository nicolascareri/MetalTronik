import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {

  navLinks: any[];
  activeLinkIndex = -1; 
  constructor(private router: Router) {
    this.navLinks = [
        {
            label: 'Existencia',
            link: '../existencia',
            index: 0
        }, {
            label: 'Entrada',
            link: '../entrada',
            index: 1
        }, {
            label: 'Salida',
            link: '../salida',
            index: 2
        }, {
            label: 'Ajuste',
            link: '../ajuste',
            index: 3
        } 
    ];
}
ngOnInit(): void {
  this.router.events.subscribe((res) => {
      this.activeLinkIndex = this.navLinks.indexOf(this.navLinks.find(tab => tab.link === '.' + this.router.url));
  });
}

}
