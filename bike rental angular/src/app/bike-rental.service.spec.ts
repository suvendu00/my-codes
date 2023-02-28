import { TestBed } from '@angular/core/testing';

import { BikeRentalService } from './bike-rental.service';

describe('BikeRentalService', () => {
  let service: BikeRentalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BikeRentalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
