package ro.tuc.ds2020.dtos;


import java.util.Date;
import java.util.UUID;

public class DoctorDTO extends UserDataDTO {

    public DoctorDTO(){}
    public DoctorDTO(UUID id, String role, String nameUser, Date birth_date, String gender, String adress, String username, String password) {
        super(id, role, nameUser, birth_date, gender, adress, username, password);
    }

    public DoctorDTO(String role, String nameUser, Date birth_date, String gender, String adress, String username, String password) {
        super(role, nameUser, birth_date, gender, adress, username, password);
    }
}
