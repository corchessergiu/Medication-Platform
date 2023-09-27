import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaregiverDataComponent } from './caregiver-data.component';

describe('CaregiverDataComponent', () => {
  let component: CaregiverDataComponent;
  let fixture: ComponentFixture<CaregiverDataComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CaregiverDataComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CaregiverDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
