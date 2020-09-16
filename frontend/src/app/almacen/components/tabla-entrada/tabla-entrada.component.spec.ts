import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaEntradaComponent } from './tabla-entrada.component';

describe('TablaEntradaComponent', () => {
  let component: TablaEntradaComponent;
  let fixture: ComponentFixture<TablaEntradaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablaEntradaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaEntradaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
