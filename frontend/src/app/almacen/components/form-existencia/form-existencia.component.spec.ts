import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormExistenciaComponent } from './form-existencia.component';

describe('FormExistenciaComponent', () => {
  let component: FormExistenciaComponent;
  let fixture: ComponentFixture<FormExistenciaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormExistenciaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormExistenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
