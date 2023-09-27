import { Component, OnInit } from '@angular/core';
import { RepositoryService } from 'src/services/repository.service';
import { TableDataMedication } from '../interfaces/tableDataMedication.model';
import { TableDataMedicationPlan } from '../interfaces/tableDataMedicationPlan.model';
import { TableDataPatient } from '../interfaces/tableDataPatient.model';
import {User} from '../interfaces/user.model';
@Component({
  selector: 'app-patient-data',
  templateUrl: './patient-data.component.html',
  styleUrls: ['./patient-data.component.css']
})
export class PatientDataComponent implements OnInit {
  public patient : TableDataPatient;
  public genderM:boolean=false;
  public user:User=JSON.parse(localStorage.getItem('user'))
  public lista:TableDataMedication[];
  public medicationPlan:TableDataMedicationPlan;
  constructor(private readonly repositoryService: RepositoryService) { }

  ngOnInit(): void {  
    this.patient=JSON.parse(localStorage.getItem('user'))
    this.listOfMedications();
  }

  public listOfMedications(){
      this.repositoryService.getData(`patient/getPatient/${this.user.username}`).
          subscribe((patient : TableDataPatient) => {
            this.patient= patient;
              if(this.patient.gender==="M")
                  this.genderM=true;
                  else
                  this.genderM=false;
                  this.repositoryService.getData(`medicationPlan/${patient.medicationPlan.id}`).
                  subscribe((plan : TableDataMedicationPlan) => {
                    this.medicationPlan= plan;
                    this.lista=this.medicationPlan.medicationList;
                 },       
      (error: any) => {
      })
            
         })
      }


}
