import { TestBed } from '@angular/core/testing';

import { ParteService } from './parte.service';

describe('ParteService', () => {
  let service: ParteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ParteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
