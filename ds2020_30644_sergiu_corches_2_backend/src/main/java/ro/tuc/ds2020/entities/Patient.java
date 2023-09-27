package ro.tuc.ds2020.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;


@Entity
@Table (name="patient")
public class Patient extends UserData implements Serializable {

    @Column(name = "medical_record", nullable = false)
    private String medical_record;

    @OneToOne(cascade={CascadeType.MERGE},fetch = FetchType.EAGER)
    private MedicationPlan medicationPlan;

    Patient(){}
    public Patient(String medical_record, MedicationPlan medicationPlan) {
        this.medical_record = medical_record;
        this.medicationPlan = medicationPlan;
    }

    public Patient(UUID id, String username, String password, String role, String nameUser, Date birth_date, String gender, String adress, String medical_record, MedicationPlan medicationPlan) {
        super(id, username, password, role, nameUser, birth_date, gender, adress);
        this.medical_record = medical_record;
        this.medicationPlan = medicationPlan;
    }

    public Patient(String username, String password, String role, String nameUser, Date birth_date, String gender, String adress, String medical_record, MedicationPlan medicationPlan) {
        super(username, password, role, nameUser, birth_date, gender, adress);
        this.medical_record = medical_record;
        this.medicationPlan = medicationPlan;
    }

    public String getMedical_record() {
        return medical_record;
    }

    public MedicationPlan getMedicationPlan() {
        return medicationPlan;
    }

    public void setMedical_record(String medical_record) {
        this.medical_record = medical_record;
    }

    public void setMedicationPlan(MedicationPlan medicationPlan) {
        this.medicationPlan = medicationPlan;
    }
}
