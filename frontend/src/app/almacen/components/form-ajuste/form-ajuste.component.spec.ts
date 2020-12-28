import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormAjusteComponent } from './form-ajuste.component';

describe('FormAjusteComponent', () => {
  let component: FormAjusteComponent;
  let fixture: ComponentFixture<FormAjusteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormAjusteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormAjusteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
