import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FromComponent } from './from.component';

describe('FromComponent', () => {
  let component: FromComponent;
  let fixture: ComponentFixture<FromComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FromComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FromComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
