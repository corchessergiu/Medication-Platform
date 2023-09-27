package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;

import java.util.ArrayList;


public class CaregiverBuilder {

    private CaregiverBuilder() {
    }

    public static CaregiverDTO toCaregiverDTO(Caregiver caregiver) {
        ArrayList<PatientDTO> patientDTOS=new ArrayList<PatientDTO>();
        for(Patient patient:caregiver.getPatients()){
            patientDTOS.add(PatientBuilder.toPatientDTO(patient));
        }
        return new CaregiverDTO(caregiver.getId(),
                caregiver.getRole(),
                caregiver.getNameUser(),
                caregiver.getBirth_date(),
                caregiver.getGender(),
                caregiver.getAdress(),
                caregiver.getUsername(),
                caregiver.getPassword(),
                patientDTOS);
    }

    public static Caregiver toEntity(CaregiverDTO caregiverDTO) {
        ArrayList<Patient> patients = new ArrayList<Patient>();
        if(caregiverDTO.getPatients()!=null) {
            for (PatientDTO patientDTO : caregiverDTO.getPatients()) {
                patients.add(PatientBuilder.toEntity(patientDTO));
            }
        }
        return new Caregiver(caregiverDTO.getId(),caregiverDTO.getUsername(), caregiverDTO.getPassword(),caregiverDTO.getRole(),
                caregiverDTO.getNameUser(), caregiverDTO.getBirth_date(), caregiverDTO.getGender(),
                caregiverDTO.getAdress(), patients);
    }
}
