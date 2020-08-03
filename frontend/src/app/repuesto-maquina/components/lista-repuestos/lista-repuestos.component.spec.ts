import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaRepuestosComponent } from './lista-repuestos.component';

describe('ListaRepuestosComponent', () => {
  let component: ListaRepuestosComponent;
  let fixture: ComponentFixture<ListaRepuestosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListaRepuestosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaRepuestosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
