import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TablaSectorComponent} from './tabla-sector.component';

describe('TablaSectorComponent', () => {
  let component: TablaSectorComponent;
  let fixture: ComponentFixture<TablaSectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TablaSectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaSectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
