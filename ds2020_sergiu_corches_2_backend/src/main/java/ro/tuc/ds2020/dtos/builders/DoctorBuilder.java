package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Patient;


public class DoctorBuilder {

    private DoctorBuilder() {
    }

    public static DoctorDTO toDoctorDTO(Doctor doctor) {
        return new DoctorDTO(doctor.getId(),
                doctor.getRole(),
                doctor.getNameUser(),
                doctor.getBirth_date(),
                doctor.getGender(),
                doctor.getAdress(),
                doctor.getUsername(),
                doctor.getPassword()
        );
    }

    public static Doctor toEntity(DoctorDTO doctorDTO) {
        return new Doctor(doctorDTO.getId(),doctorDTO.getUsername(), doctorDTO.getPassword(),doctorDTO.getRole(),
                doctorDTO.getNameUser(),doctorDTO.getBirth_date(),doctorDTO.getGender(),
                doctorDTO.getAdress());
    }
}
