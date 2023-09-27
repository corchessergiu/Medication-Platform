import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {RepositoryService} from 'src/services/repository.service';
import {TableDataPatient} from '../interfaces/tableDataPatient.model'
import {TableDataMedicationPlan} from '../interfaces/tableDataMedicationPlan.model';
import { TableDataMedication } from '../interfaces/tableDataMedication.model';
import { FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-crud-patient',
  templateUrl: './crud-patient.component.html',
  styleUrls: ['./crud-patient.component.css']
})
export class CrudPatientComponent implements OnInit {

  constructor(public router: Router,private readonly repositoryService: RepositoryService) { }

  public patients:TableDataPatient[];
  public patientsMedicationPlan:TableDataMedicationPlan;
  public lista:TableDataMedication[];
  public view:boolean=false;
  public addForm: FormGroup;
  public editForm:FormGroup;
  public idUpdate:String;

  ngOnInit(): void {
    this.getData();

    this.addForm = new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
      nameUser: new FormControl(''),
      role: new FormControl(''),
      birth_date: new FormControl(''),
      gender: new FormControl(''),
      adress: new FormControl(''),
      medical_record: new FormControl('')
    });

    this.editForm = new FormGroup({
      nameUser: new FormControl(''),
      role: new FormControl(''),
      birth_date: new FormControl(''),
      gender: new FormControl(''),
      adress: new FormControl(''),
      medical_record: new FormControl(''),
    });
  }




  public getData(){
    this.repositoryService.getData('patient').
    subscribe((patients : TableDataPatient[]) => {
      this.patients = patients;

   })
  }

  
 public   getDataRecord(){
    this.repositoryService.getData('patient').
    subscribe((patients : TableDataPatient[]) => {
      this.patients = patients;
   })
   return this.patients;
  }

  public getId(idPatient){
    this.idUpdate=idPatient;
  } 

  public viewInfo(id){
    this.view=true;
  
    this.repositoryService.getData(`medicationPlan/${id}`).
    subscribe((plan : TableDataMedicationPlan) => {
      this.patientsMedicationPlan = plan;

      this.lista=this.patientsMedicationPlan.medicationList;
   })

  }

  public deletePatient(idPatient){
    this.repositoryService.delete(`patient/${idPatient}`).
    subscribe(() => {
      console.log("Delete success!!!");
      this.getData();
   }),
   (error: any) => {
   console.log("EROARE DELETE")
   }
  }

  onSubmit(valoare){
    let patient:TableDataPatient={
      username: valoare.username,
      password:  valoare.password,
      nameUser: valoare.nameUser,
      birth_date: valoare.birth_date,
      gender:  valoare.gender,
      adress:  valoare.adress,
      medical_record:  valoare.medical_record,
      role: 'PATIENT',
      medicationPlan:null
    } 
    this.repositoryService.create("patient", patient)
    .subscribe(res => {
      this.getData();
    },
      (error: any) => {
      console.log("EROARE POST PATIENT!!!")
      }) 
     }
  

     onSubmitEdit(valoare){
      let newpatient:TableDataPatient={
        nameUser: valoare.nameUser,
        birth_date: valoare.birth_date,
        gender:  valoare.gender,
        adress:  valoare.adress,
        medical_record:  valoare.medical_record,
        role: 'PATIENT',
        medicationPlan:null
      } 
      this.repositoryService.update(`patient/${this.idUpdate}`,newpatient) .subscribe(res => {
        this.getData();
      },
        (error: any) => {
        console.log("EROARE UPDATE PATIENT!!")
        }) 

    }
}
