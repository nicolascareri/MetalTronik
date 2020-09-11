import { TestBed } from '@angular/core/testing';

import { AlmacenService } from './almacen.service';

describe('AlmacenService', () => {
  let service: AlmacenService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AlmacenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
