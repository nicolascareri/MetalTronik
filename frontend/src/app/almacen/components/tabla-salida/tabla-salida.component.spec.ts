import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaSalidaComponent } from './tabla-salida.component';

describe('TablaSalidaComponent', () => {
  let component: TablaSalidaComponent;
  let fixture: ComponentFixture<TablaSalidaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablaSalidaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaSalidaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
