import { Component, OnInit, Input, SimpleChanges, Output, EventEmitter } from '@angular/core';
import { CoreService } from 'src/app/core/service/core.service';

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styleUrls: ['./tabla.component.scss']
})
export class TablaComponent implements OnInit {
  
  @Input() actionForButton : any;
  @Input() includeButton = true;
  @Input() routeForButton = "route";
  @Input() nameForButton= "nameForButton";
  @Input() title = "title";
  @Input() columns: any;
  @Input() ds: any = [{}];
  @Input() width = '100%';
  @Input() search = true;
  @Input() paginated = false;
  @Input() pageSize;
  @Output() clickRow  = new EventEmitter();


  public isLoading = true;
  public displayData = [];
  public pageableData = [];
  public errorMessage = '';
  public sortedFieldId = 1;
  public pager: any = {};
  public pagedItems: any[];
  public hasClickRowFunction: boolean;

  private filters = [];

  constructor( private core: CoreService ) { }

  ngOnInit(): void {
    this.pageableData = this.displayData = this.ds;
    if (this.paginated) {
      this.setPage(1);
    }
    this.columns.forEach(field => {
      if (field.filterValue && field.filterValue !== '' ) {
        this.filter(field.property, field.filterValue);
      }
    });

    this.displayData = this.ds;
    if (!this.ds || ! this.columns) {
      this.errorMessage = 'Error al instanciar la tabla. Revisar parámetros de input (ds o columns no esta siendo incluido)';
    }
    this.hasClickRowFunction = !!this.clickRow.observers.length;
  }

  recordSelected(row: any) {
    this.clickRow.emit(row);
  }  

  ngOnChanges(changes: SimpleChanges): void {
    if ( changes.ds ) {
      this.ds = this.ds ? this.ds : [];
      this.displayData = this.ds;
    }
  }
  filter(field: string, value: string) {

    // agrego o modifico el valor en el listado de filtros activos
    const valueIndex = this.filters.findIndex(val => val.id === field);
    if (valueIndex === -1) {
      this.filters.push({ id: field, val: value.trim() });
    } else {
      this.filters[valueIndex] = { id: field, val: value.trim() };
    }

    // realizo el filtro sobre la estructura de datos a mostrar en pantalla
    this.firterData();

    if (this.paginated) {
      this.pageableData = this.displayData;

      // initialize to page 1
      this.setPage(1);
    }
  }

  firterData() {
    this.displayData = this.ds.filter(data => {
      let find = true;
      this.filters.forEach(query => {
        const val = query.val.toLowerCase();
        const field = data[query.id] ? data[query.id].toString().toLowerCase() : '';
        if (query.val !== '' && !field.includes(val)) {
          find = false;
        }
      });
      return find;
    });
  }

  sortBy(id: any, field: string) {
    const ind = this.columns.findIndex( x => x.id === id );
    this.sortedFieldId = id;

    if (ind >= 0) {
      if (this.columns[ind].sort === 'up') {
         this.columns[ind].sort = 'down';
      } else {
        this.columns[ind].sort = 'up';
      }
    }

    const sortedData = this.paginated ? this.pageableData : this.displayData;
    sortedData.sort( (a, b) => {


      let firstValue;
      let secondValue;

      // si es un codigo entonces es un number
      if (typeof(a[field]) === 'number' && typeof(b[field]) === 'number') {
        firstValue = a[field];
        secondValue = b[field];
      }
      // si es un string, puede que sea un int como por ejemplo un cuit
      if (typeof(a[field]) === 'string' && typeof(b[field]) === 'string') {
        firstValue = isNaN(a[field]) ?  a[field].toLowerCase() : parseInt(a[field], 10) ;
        secondValue = isNaN(b[field]) ? b[field].toLowerCase() : parseInt(b[field], 10) ;
      }

      if (firstValue > secondValue) {
        return this.columns[ind].sort === 'up' ? 1 : -1;
      }
      if (firstValue < secondValue) {
        return this.columns[ind].sort === 'up' ? -1 : 1;
      }
      return 0;

    });

    if (this.paginated) {
      this.pageableData = sortedData;
      // initialize to page 1
      this.setPage(1);
    }
  }
  setPage(page: number) {
    if (page < 1 || (page > this.pager.totalPages && this.pager.totalPages !== 0) ) {
        return;
    }

    // get pager object from service
    this.pager = this.core.getPager(this.pageableData.length, page, this.pageSize ? this.pageSize : 10, this.pageableData);

    // get current page of items
    this.displayData = this.pageableData.slice(this.pager.startIndex, this.pager.endIndex + 1);
  }


}
