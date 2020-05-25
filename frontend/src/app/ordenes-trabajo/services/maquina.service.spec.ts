import { TestBed } from '@angular/core/testing';

import { MaquinaService } from './maquina.service';

describe('MaquinaService', () => {
  let service: MaquinaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MaquinaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
