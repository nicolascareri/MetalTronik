import { TestBed } from '@angular/core/testing';

import { SalidaService } from './salida.service';

describe('SalidaService', () => {
  let service: SalidaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SalidaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
