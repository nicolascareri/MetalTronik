import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPartesComponent } from './form-partes.component';

describe('FormPartesComponent', () => {
  let component: FormPartesComponent;
  let fixture: ComponentFixture<FormPartesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormPartesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormPartesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
