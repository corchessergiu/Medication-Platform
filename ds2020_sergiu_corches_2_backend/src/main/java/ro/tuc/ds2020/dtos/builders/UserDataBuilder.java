package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.UserDataDTO;
import ro.tuc.ds2020.entities.UserData;


public class UserDataBuilder {

    private UserDataBuilder() {
    }

    public static UserDataDTO toUserDataDTO(UserData userData) {
        return new UserDataDTO(userData.getId(),
                userData.getRole(),
                userData.getNameUser(),
                userData.getBirth_date(),
                userData.getGender(),
                userData.getAdress(),
                userData.getUsername(),
                userData.getPassword()
        );
    }

 /*   public static Patient toEntity(PatientDTO patientDTO) {
        return new Patient(patientDTO.getId(),patientDTO.getUsername(), patientDTO.getPassword(),patientDTO.getRole(),
                patientDTO.getNameUser(),patientDTO.getBirth_date(),patientDTO.getGender(),
                patientDTO.getAdress(),patientDTO.getMedical_record(),patientDTO.getMedicationPlan());
    }*/
}
