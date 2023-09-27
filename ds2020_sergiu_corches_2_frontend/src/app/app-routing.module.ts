import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import {DoctorComponent} from './doctor/doctor.component';
import {CrudPatientComponent} from './crud-patient/crud-patient.component';
import {CrudMedicationComponent} from './crud-medication/crud-medication.component';
import {CrudCaregiverComponent} from './crud-caregiver/crud-caregiver.component';
import{CrudMedicationPlanComponent } from './crud-medication-plan/crud-medication-plan.component';
import { LoginComponent } from './login/login.component';
import { PatientDataComponent } from './patient-data/patient-data.component';
import { StartPageComponent } from './start-page/start-page.component';
import { DoctorGuardGuard } from './doctor-guard.guard';
import { PatientGuardGuard } from './patient-guard.guard';
import { CaregiverDataComponent } from './caregiver-data/caregiver-data.component';
import { CaregiverGuardGuard } from './caregiver-guard.guard';
const routes: Routes = [
  {path: '', component: DoctorComponent },
  {path: 'doctor',
  canActivate:[DoctorGuardGuard],
  children:[
         {path: 'crudPatient', component: CrudPatientComponent }, 
        {path: 'crudMedication', component: CrudMedicationComponent },
        {path: 'crudCaregiver', component: CrudCaregiverComponent },
        {path: 'addMedicationPlan', component: CrudMedicationPlanComponent }
  ]},

  {path: 'login', component: LoginComponent},
  {path: 'patient/data',    canActivate:[PatientGuardGuard],component: PatientDataComponent},
  {path: 'start', component: StartPageComponent},
  
  {path: 'caregiver/data',  canActivate:[CaregiverGuardGuard],component: CaregiverDataComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes), CommonModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
