import { TableDataMedicationPlan } from './tableDataMedicationPlan.model';


export interface TableDataPatient{
    id?:String;
    username?:String;
    password?:String;
    role:String;
    nameUser:String;
    birth_date:String;
    gender:String;
    adress:String;
    medical_record:String;
    medicationPlan?:TableDataMedicationPlan;
}

