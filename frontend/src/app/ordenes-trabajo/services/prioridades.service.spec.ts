import { TestBed } from '@angular/core/testing';

import { PrioridadesService } from './prioridades.service';

describe('PrioridadesService', () => {
  let service: PrioridadesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PrioridadesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
