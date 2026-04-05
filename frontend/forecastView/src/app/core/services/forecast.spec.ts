import { TestBed } from '@angular/core/testing';

import { ForecastService } from './forecast';

describe('Forecast', () => {
  let service: ForecastService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ForecastService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
