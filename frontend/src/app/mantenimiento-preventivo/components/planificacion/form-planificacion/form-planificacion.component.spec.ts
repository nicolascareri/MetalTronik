import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPlanificacionComponent } from './form-planificacion.component';

describe('FormPlanificacionComponent', () => {
  let component: FormPlanificacionComponent;
  let fixture: ComponentFixture<FormPlanificacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormPlanificacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormPlanificacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
