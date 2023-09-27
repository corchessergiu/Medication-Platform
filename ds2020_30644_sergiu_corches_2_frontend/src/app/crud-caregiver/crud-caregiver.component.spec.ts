import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CrudCaregiverComponent } from './crud-caregiver.component';

describe('CrudCaregiverComponent', () => {
  let component: CrudCaregiverComponent;
  let fixture: ComponentFixture<CrudCaregiverComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CrudCaregiverComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CrudCaregiverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
