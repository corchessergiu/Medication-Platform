package ro.tuc.ds2020.entities;


import org.hibernate.criterion.NotNullExpression;
import org.junit.Test;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="caregiver")
public class Caregiver extends UserData {



    @ManyToMany(
            fetch=FetchType.EAGER)
    private List<Patient> patients;

    public Caregiver(){}
    public Caregiver(List<Patient> patients) {
        this.patients = patients;
    }

    public Caregiver(UUID id, String username, String password, String role, String nameUser, Date birth_date, String gender, String adress, List<Patient> patients) {
        super(id, username, password, role, nameUser, birth_date, gender, adress);
        this.patients = patients;
    }

    public Caregiver(String username, String password, String role, String nameUser, Date birth_date, String gender, String adress, List<Patient> patients) {
        super(username, password, role, nameUser, birth_date, gender, adress);
        this.patients = patients;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
