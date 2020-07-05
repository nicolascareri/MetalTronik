import { TestBed } from '@angular/core/testing';

import { TipoService } from './tipo.service';

describe('TipoService', () => {
  let service: TipoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TipoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
