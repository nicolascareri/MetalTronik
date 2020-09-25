import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaExistenciaComponent } from './tabla-existencia.component';

describe('TablaExistenciaComponent', () => {
  let component: TablaExistenciaComponent;
  let fixture: ComponentFixture<TablaExistenciaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablaExistenciaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaExistenciaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
