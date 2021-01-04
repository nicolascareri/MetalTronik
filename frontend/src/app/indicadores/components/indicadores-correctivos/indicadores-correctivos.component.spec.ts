import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IndicadoresCorrectivosComponent } from './indicadores-correctivos.component';

describe('IndicadoresCorrectivosComponent', () => {
  let component: IndicadoresCorrectivosComponent;
  let fixture: ComponentFixture<IndicadoresCorrectivosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IndicadoresCorrectivosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IndicadoresCorrectivosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
