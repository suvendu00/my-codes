import { TestBed } from '@angular/core/testing';

import { RefillService } from './refill-service.service';

describe('RefillServiceService', () => {
  let service: RefillService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RefillService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
