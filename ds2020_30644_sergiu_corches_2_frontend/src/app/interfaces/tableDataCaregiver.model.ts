import { TableDataPatient } from './tableDataPatient.model';


export interface TableDataCaregiver{
    id?:String;
    username?:String;
    password?:String;
    role:String;
    nameUser:String;
    birth_date:String;
    gender:String;
    adress:String;
    patients?:TableDataPatient[];
}

