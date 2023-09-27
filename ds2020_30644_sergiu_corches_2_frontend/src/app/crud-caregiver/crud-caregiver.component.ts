import { Component, OnInit } from '@angular/core';
import {RepositoryService} from 'src/services/repository.service';
import { FormControl, FormGroup} from '@angular/forms';
import { TableDataCaregiver } from '../interfaces/tableDataCaregiver.model';
import { TableDataPatient } from '../interfaces/tableDataPatient.model';
@Component({
  selector: 'app-crud-caregiver',
  templateUrl: './crud-caregiver.component.html',
  styleUrls: ['./crud-caregiver.component.css']
})
export class CrudCaregiverComponent implements OnInit {

  public addForm: FormGroup;
  public editForm: FormGroup;
  public caregivers:TableDataCaregiver[];
  public patients:TableDataPatient[];
  public NewatientsList:TableDataPatient[];
  public patientsList:TableDataPatient[]=[];
  public addPatientData:boolean=false;
  public idUpdate;
  public idUppCaregiverListPatient;
  public idPatientAdd;
  public patientList=false;
  public eroare=false;
  constructor(private readonly repositoryService: RepositoryService) { }
  ngOnInit(): void {
    this.getData();
    this.addForm = new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
      nameUser: new FormControl(''),
      role: new FormControl(''),
      birth_date: new FormControl(''),
      gender: new FormControl(''),
      adress: new FormControl('')
    });

 
    this.editForm = new FormGroup({
      nameUser: new FormControl(''),
      role: new FormControl(''),
      birth_date: new FormControl(''),
      gender: new FormControl(''),
      adress: new FormControl('')
    });

  }
    

  public getData(){
    this.repositoryService.getData('caregiver').
    subscribe((caregivers : TableDataCaregiver[]) => {
      this.caregivers = caregivers;
   })
  }

 public alertDelete(){
  this.eroare=false;
}
  deleteCaregiver(id){
    this.repositoryService.delete(`caregiver/${id}`).
    subscribe(() => {
      this.getData();
   },
   (error:any) => {  
   })
  }

  public onSubmitAdd(valoare){

    let caregiver:TableDataCaregiver={
      username: valoare.username,
      password:  valoare.password,
      nameUser: valoare.nameUser,
      birth_date: valoare.birth_date,
      gender:  valoare.gender,
      adress:  valoare.adress,
      role: 'CAREGIVER'
    }
    this.repositoryService.create("caregiver", caregiver)
    .subscribe(res => {
      this.getData();
    },
      (error: any) => {
      console.log("EROARE POST CAREGIVER!!!")
      }) 
  }

  public addPatient(id){
    this.idUppCaregiverListPatient=id;
    this.addPatientData=true;
    this.patientList=false;
    this.repositoryService.getData('patient').
    subscribe((patients : TableDataPatient[]) => {
      this.patients = patients;
   })
  }

  public viewPatient(id){
    this.patientList=false;
    this.addPatientData=false;
    let caregiverView:TableDataCaregiver;
    this.repositoryService.getData(`caregiver/${id}`).
    subscribe((res:TableDataCaregiver) => {
      this.NewatientsList=res.patients;
      this.patientList=true;
   },
   (error:any) => {  
   })
  }

public closeTable(){
  this.patientList=false;
  this.addPatientData=false;
}
  public getIdUpdate(id){
    this.idUpdate=id;
    this.patientList=false;
    this.addPatientData=false;
  }

  public onSubmitEdit(valoare){
    let newcaregiver:TableDataCaregiver={
      nameUser: valoare.nameUser,
      birth_date: valoare.birth_date,
      gender:  valoare.gender,
      adress:  valoare.adress,
      role: 'CAREGIVER',
    } 
    
    this.repositoryService.update(`caregiver/${this.idUpdate}`,newcaregiver) .subscribe(res => {
      this.getData();
    },
      (error: any) => {
      console.log("EROARE UPDATE caregiver!!")
      })  
  }


 public  patientSelection(input: HTMLInputElement) {
    let idPatient=input.value;
     for(let pat of this.patients){
      if(pat.id===idPatient){
        this.patientsList.push(pat);
      }
    } 
}
 public addPatitntsToCaregiver(){
   let caregiverUpp:TableDataCaregiver;
  for (let caregiver of this.caregivers){
    if(this.idUppCaregiverListPatient===caregiver.id)
    {
        caregiverUpp=caregiver;
        break;
    }
  }
  caregiverUpp.patients=this.patientsList;
  for(let val of caregiverUpp.patients){
    console.log(val.nameUser);
  }
  this.repositoryService.update(`caregiver/addPatients/${this.idUppCaregiverListPatient}`,caregiverUpp) .subscribe(res => {
    this.getData();
    this.addPatientData=false;
  },
    (error: any) => {
      this.eroare=true;
    })  
 }

}
