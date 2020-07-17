import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TablaPlantaComponent} from './tabla-planta.component';

describe('TablaPlantaComponent', () => {
  let component: TablaPlantaComponent;
  let fixture: ComponentFixture<TablaPlantaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablaPlantaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaPlantaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
