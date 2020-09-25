import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormSalidaComponent } from './form-salida.component';

describe('FormSalidaComponent', () => {
  let component: FormSalidaComponent;
  let fixture: ComponentFixture<FormSalidaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormSalidaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormSalidaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
