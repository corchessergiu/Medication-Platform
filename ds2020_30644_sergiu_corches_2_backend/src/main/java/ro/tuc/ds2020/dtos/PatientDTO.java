package ro.tuc.ds2020.dtos;


import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.MedicationPlan;

import java.util.Date;
import java.util.UUID;

public class PatientDTO extends UserDataDTO {

    private String medical_record;
    private MedicationPlan medicationPlan;

    public PatientDTO(){}
    public PatientDTO(String medical_record, MedicationPlan medicationPlan) {
        this.medical_record = medical_record;
        this.medicationPlan = medicationPlan;
    }

    public PatientDTO(UUID id, String role, String nameUser, Date birth_date, String gender, String adress, String username, String password, String medical_record, MedicationPlan medicationPlan) {
        super(id, role, nameUser, birth_date, gender, adress, username, password);
        this.medical_record = medical_record;
        this.medicationPlan = medicationPlan;
    }

    public PatientDTO(String role, String nameUser, Date birth_date, String gender, String adress, String username, String password, String medical_record, MedicationPlan medicationPlan) {
        super(role, nameUser, birth_date, gender, adress, username, password);
        this.medical_record = medical_record;
        this.medicationPlan = medicationPlan;
    }

    public void setMedical_record(String medical_record) {
        this.medical_record = medical_record;
    }

    public void setMedicationPlan(MedicationPlan medicationPlan) {
        this.medicationPlan = medicationPlan;
    }

    public String getMedical_record() {
        return medical_record;
    }

    public MedicationPlan getMedicationPlan() {
        return medicationPlan;
    }
}
