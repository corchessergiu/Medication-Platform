package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.entities.Patient;


public class PatientBuilder {

    private PatientBuilder() {
    }

    public static PatientDTO toPatientDTO(Patient patient) {
        return new PatientDTO(patient.getId(),
                patient.getRole(),
                patient.getNameUser(),
                patient.getBirth_date(),
                patient.getGender(),
                patient.getAdress(),
                patient.getUsername(),
                patient.getPassword(),
                patient.getMedical_record(),
                patient.getMedicationPlan()
        );
    }

    public static Patient toEntity(PatientDTO patientDTO) {
        return new Patient(patientDTO.getId(),patientDTO.getUsername(), patientDTO.getPassword(),patientDTO.getRole(),
                patientDTO.getNameUser(),patientDTO.getBirth_date(),patientDTO.getGender(),
                patientDTO.getAdress(),patientDTO.getMedical_record(),patientDTO.getMedicationPlan());
    }
}
