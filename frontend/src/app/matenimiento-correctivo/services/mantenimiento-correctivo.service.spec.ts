import {TestBed} from '@angular/core/testing';

import {MantenimientoCorrectivoService} from './mantenimiento-correctivo.service';

describe('MantenimientoCorrectivoService', () => {
  let service: MantenimientoCorrectivoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MantenimientoCorrectivoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
