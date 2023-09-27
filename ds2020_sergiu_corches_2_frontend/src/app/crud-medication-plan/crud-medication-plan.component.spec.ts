import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudMedicationPlanComponent } from './crud-medication-plan.component';

describe('CrudMedicationPlanComponent', () => {
  let component: CrudMedicationPlanComponent;
  let fixture: ComponentFixture<CrudMedicationPlanComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CrudMedicationPlanComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CrudMedicationPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
