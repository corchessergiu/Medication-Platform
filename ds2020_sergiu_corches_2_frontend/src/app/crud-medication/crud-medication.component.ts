import { Component, OnInit } from '@angular/core';
import {TableDataMedication} from '../interfaces/tableDataMedication.model';
import {RepositoryService} from 'src/services/repository.service';
import { FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-crud-medication',
  templateUrl: './crud-medication.component.html',
  styleUrls: ['./crud-medication.component.css']
})
export class CrudMedicationComponent implements OnInit {

  public medications:TableDataMedication[];
  public currentMedData:TableDataMedication;
  public addForm: FormGroup;
  public editForm: FormGroup;
  public idDelete:any;
  public idUpdate:any;
  public errorDelete=false;
  constructor( private readonly repositoryService: RepositoryService){}


  ngOnInit(){
    this.getData();
    this.addForm = new FormGroup({
      name: new FormControl(''),
      sideEffects: new FormControl(''),
      dosage: new FormControl('')
    });

    this.editForm = new FormGroup({
      name: new FormControl(''),
      sideEffects: new FormControl(''),
      dosage: new FormControl('')
    });
    

  }
  
  
   public getId(id){
    this.idUpdate=id;
   }
   

  onSubmitEdit(valoare){
    let medicationEdit:TableDataMedication={
      name:valoare.name,
      sideEffects:valoare.sideEffects,
      dosage:valoare.dosage
    }
    this.repositoryService.update(`medication/${this.idUpdate}`,medicationEdit) .subscribe(res => {
      this.getData();
    },
      (error: any) => {
      console.log("EROARE POST SELLER!!!")
      }) 

  }
  onSubmit(val){

    let medication:TableDataMedication={
      name:val.name,
      sideEffects:val.sideEffects,
      dosage:val.dosage
    }
    this.repositoryService.create("medication", medication)
    .subscribe(res => {
      this.getData();
    },
      (error: any) => {
      console.log("EROARE POST SELLER!!!")
      }) 

  }
  public getData(){
    this.repositoryService.getData('medication').
    subscribe((medications : TableDataMedication[]) => {
      this.medications = medications;
   },
   (error: any) => {
   console.log("EROARE GET DATA!!");
   })

  }



 
  deleteFieldValue(valoare){
    this.idDelete=valoare;
    this.repositoryService.delete(`medication/${this.idDelete}`).
    subscribe(() => {
      this.getData();
   },
   (error:any) => {  
    this.errorDelete=true;
   
   })
  }

  public alertDelete(){
    this.errorDelete=false;
  }

}
