import { TestBed } from '@angular/core/testing';

import { RepuestoMaquinaService } from './repuesto-maquina.service';

describe('RepuestoMaquinaService', () => {
  let service: RepuestoMaquinaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RepuestoMaquinaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
