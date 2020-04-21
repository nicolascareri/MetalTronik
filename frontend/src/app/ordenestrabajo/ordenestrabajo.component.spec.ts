import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdenestrabajoComponent } from './ordenestrabajo.component';

describe('OrdenestrabajoComponent', () => {
  let component: OrdenestrabajoComponent;
  let fixture: ComponentFixture<OrdenestrabajoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OrdenestrabajoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdenestrabajoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
