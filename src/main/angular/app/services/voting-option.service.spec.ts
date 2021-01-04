import { TestBed } from '@angular/core/testing';

import { VotingOptionService } from './voting-option.service';

describe('VotingOptionService', () => {
  let service: VotingOptionService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VotingOptionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
