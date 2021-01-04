import { TestBed } from '@angular/core/testing';

import { HouseVotingService } from './house-voting.service';

describe('HouseVotingService', () => {
  let service: HouseVotingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HouseVotingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
