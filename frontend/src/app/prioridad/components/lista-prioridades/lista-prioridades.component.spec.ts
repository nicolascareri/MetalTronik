import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaPrioridadesComponent } from './lista-prioridades.component';

describe('ListaPrioridadesComponent', () => {
  let component: ListaPrioridadesComponent;
  let fixture: ComponentFixture<ListaPrioridadesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaPrioridadesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaPrioridadesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
