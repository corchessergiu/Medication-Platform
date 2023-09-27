import { Component, OnInit } from '@angular/core';
import { TableDataPatient } from '../interfaces/tableDataPatient.model';
import {RepositoryService} from 'src/services/repository.service';
import { FormControl, FormGroup} from '@angular/forms';
import { TableDataMedicationPlan } from '../interfaces/tableDataMedicationPlan.model';
import { TableDataMedication } from '../interfaces/tableDataMedication.model';
@Component({
  selector: 'app-crud-medication-plan',
  templateUrl: './crud-medication-plan.component.html',
  styleUrls: ['./crud-medication-plan.component.css']
})
export class CrudMedicationPlanComponent implements OnInit {

  public patients:TableDataPatient[];
  constructor(private readonly repositoryService: RepositoryService) { }
  public patientUpp:TableDataPatient;
  public addForm:FormGroup;
  public medPlan:TableDataMedicationPlan;
  public medications:TableDataMedication[];
  public selectedElement:any;
  public idPatient;
  public idMedication;
  public medicationPlan:TableDataMedicationPlan;
  public medicationList:TableDataMedication[]=[];
  ngOnInit(): void {
    this.getData();
    this.getDataMedication();
    this.addForm = new FormGroup({
      interval: new FormControl(''),
      numarZile: new FormControl('')
    });
  }


  public getData(){
    this.repositoryService.getData('patient/null').
    subscribe((patients : TableDataPatient[]) => {
      this.patients = patients;
   })
  }

public getDataMedication(){
  this.repositoryService.getData('medication').
  subscribe((medications : TableDataMedication[]) => {
    this.medications = medications;
 },
 (error: any) => {
 console.log("EROARE GET DATA!!");
 })
}
public addMedicationPlan(){

}
selectionChangeMedication(input: HTMLInputElement) {
  this.idMedication=input.value;
    for(let med of this.medications){
      if(med.id===this.idMedication){
        this.medicationList.push(med);
      }
      
    }
}

selectionPatient(input: HTMLInputElement) {
  this.idPatient=input.value;
  let valPat:TableDataPatient;
  for(let pat of this.patients){
    if(pat.id===this.idPatient){
      this.patientUpp=pat;
      break;
    }
  }
}

public createMedicationPlan(valoare){
  this.repositoryService.create("medicationPlan/obj", valoare).
  subscribe((val:TableDataMedicationPlan) => {
    this.medicationPlan = val;
    this.patientUpp.medicationPlan=val;
    this.repositoryService.update(`patient/${this.idPatient}`, this.patientUpp) .subscribe(res => {
      this.getData();
    },
      (error: any) => {
      console.log("EROARE UPDATE PATIENT!!")
      })

 }),
    (error: any) => {
    }
}
public onSubmit(valoare){
  
  let medication:TableDataMedicationPlan={
    interval: valoare.interval,
    numarZile:  valoare.numarZile,
  }
  medication.medicationList=this.medicationList;
  this.createMedicationPlan(medication);
} 



}
