import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaPlanificacionComponent } from './tabla-planificacion.component';

describe('TablaPlanificacionComponent', () => {
  let component: TablaPlanificacionComponent;
  let fixture: ComponentFixture<TablaPlanificacionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablaPlanificacionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaPlanificacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
