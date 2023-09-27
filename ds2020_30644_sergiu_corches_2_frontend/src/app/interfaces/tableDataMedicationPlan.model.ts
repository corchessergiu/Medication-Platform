import { TableDataMedication } from './tableDataMedication.model';


export interface TableDataMedicationPlan{
    id?:number;
    interval:String;
    numarZile:number;
    medicationList?:TableDataMedication[];
}

