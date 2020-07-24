import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaRepuestosComponent } from './tabla-repuestos.component';

describe('TablaRepuestosComponent', () => {
  let component: TablaRepuestosComponent;
  let fixture: ComponentFixture<TablaRepuestosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablaRepuestosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaRepuestosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
