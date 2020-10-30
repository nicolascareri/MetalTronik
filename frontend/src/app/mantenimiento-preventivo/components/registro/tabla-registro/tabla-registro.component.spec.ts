import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaRegistroComponent } from './tabla-registro.component';

describe('TablaRegistroComponent', () => {
  let component: TablaRegistroComponent;
  let fixture: ComponentFixture<TablaRegistroComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablaRegistroComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaRegistroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
