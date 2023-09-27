package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.Patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CaregiverDTO extends UserDataDTO {


    private List<PatientDTO> patients;

    public CaregiverDTO(){
    }

    public CaregiverDTO(UUID id, String role, String nameUser, Date birth_date, String gender, String adress, String username, String password, List<PatientDTO> patients) {
        super(id, role, nameUser, birth_date, gender, adress, username, password);
        this.patients = patients;
    }

    public CaregiverDTO(String role, String nameUser, Date birth_date, String gender, String adress, String username, String password, List<PatientDTO> patients) {
        super(role, nameUser, birth_date, gender, adress, username, password);
        this.patients = patients;
    }

    public void setPatients(List<PatientDTO> patients) {
        this.patients = patients;
    }

    public List<PatientDTO> getPatients() {
        return patients;
    }
}
