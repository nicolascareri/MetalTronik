import {TestBed} from '@angular/core/testing';

import {OrdenestrabajoService} from './ordenestrabajo.service';

describe('OrdenestrabajoService', () => {
  let service: OrdenestrabajoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OrdenestrabajoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
