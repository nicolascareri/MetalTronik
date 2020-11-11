import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormPartesIIComponent } from './form-partes-ii.component';

describe('FormPartesIIComponent', () => {
  let component: FormPartesIIComponent;
  let fixture: ComponentFixture<FormPartesIIComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormPartesIIComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormPartesIIComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
