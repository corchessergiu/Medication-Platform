import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { DoctorComponent } from './doctor/doctor.component';
import { CrudPatientComponent } from './crud-patient/crud-patient.component';
import { CrudMedicationComponent } from './crud-medication/crud-medication.component';

import {RepositoryService} from 'src/services/repository.service';
import {EnvironmentUrlService} from 'src/services/environement-url.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule }   from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CrudCaregiverComponent } from './crud-caregiver/crud-caregiver.component';
import { CrudMedicationPlanComponent } from './crud-medication-plan/crud-medication-plan.component';
import { LoginComponent } from './login/login.component';
import { AuthService } from 'src/services/auth.service';
import { AuthInterceptor } from './auth.interceptor';
import { PatientDataComponent } from './patient-data/patient-data.component';
import { CommonModule } from '@angular/common';
import { StartPageComponent } from './start-page/start-page.component';
import { CaregiverDataComponent } from './caregiver-data/caregiver-data.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    DoctorComponent,
    CrudPatientComponent,
    CrudMedicationComponent,
    CrudCaregiverComponent,
    CrudMedicationPlanComponent,
    LoginComponent,
    PatientDataComponent,
    StartPageComponent,
    CaregiverDataComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule
  ],
  providers: [EnvironmentUrlService,
              AuthService,
              {
                provide: HTTP_INTERCEPTORS,
                useClass: AuthInterceptor,
                multi: true
              },
             RepositoryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
