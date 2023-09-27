import { TestBed } from '@angular/core/testing';

import { CaregiverGuardGuard } from './caregiver-guard.guard';

describe('CaregiverGuardGuard', () => {
  let guard: CaregiverGuardGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(CaregiverGuardGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
