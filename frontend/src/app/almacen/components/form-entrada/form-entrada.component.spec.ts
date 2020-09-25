import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormEntradaComponent } from './form-entrada.component';

describe('FormEntradaComponent', () => {
  let component: FormEntradaComponent;
  let fixture: ComponentFixture<FormEntradaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormEntradaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormEntradaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
