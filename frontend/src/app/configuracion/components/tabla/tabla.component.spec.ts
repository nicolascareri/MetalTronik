import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TablaConfiguracionComponent } from './tabla.component';

describe('TablaComponent', () => {
  let component: TablaConfiguracionComponent;
  let fixture: ComponentFixture<TablaConfiguracionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [TablaConfiguracionComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TablaConfiguracionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
