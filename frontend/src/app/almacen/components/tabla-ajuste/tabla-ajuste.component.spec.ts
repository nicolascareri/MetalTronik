import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaAjusteComponent } from './tabla-ajuste.component';

describe('TablaAjusteComponent', () => {
  let component: TablaAjusteComponent;
  let fixture: ComponentFixture<TablaAjusteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablaAjusteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaAjusteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
