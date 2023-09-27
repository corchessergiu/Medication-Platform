package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.builders.CaregiverBuilder;
import ro.tuc.ds2020.dtos.builders.DoctorBuilder;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.entities.UserData;
import ro.tuc.ds2020.repositories.DoctorRepository;
import ro.tuc.ds2020.repositories.PatientRepository;


import javax.print.Doc;
import javax.transaction.Transactional;
import javax.xml.parsers.DocumentBuilder;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);
    private final DoctorRepository doctorRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorDTO> findDoctor() {
        List<Doctor> doctorList = doctorRepository.findAll();
        return doctorList.stream()
                .map(DoctorBuilder::toDoctorDTO)
                .collect(Collectors.toList());
    }

    public DoctorDTO findDoctorById(UUID id) {
        Optional<Doctor> prosumerOptional = doctorRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Doctor with id {} was not found in db", id);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + id);
        }
        return DoctorBuilder.toDoctorDTO(prosumerOptional.get());
    }

    public UUID insert(DoctorDTO doctorDTO) {
        String pass=doctorDTO.getPassword();
        Doctor doctor = DoctorBuilder.toEntity(doctorDTO);
        doctor.setPassword(bCryptPasswordEncoder.encode(doctorDTO.getPassword()));
        doctor = doctorRepository.save(doctor);
        LOGGER.debug("Patient with id {} was inserted in db", doctor.getId());
        return doctor.getId();
    }

    public void deleteDoctorById(UUID id)
    {
        Optional<Doctor> prosumerOptional = doctorRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Doctor with id {} was not found in db", id);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + id+" was not found!");
        }
        doctorRepository.deleteById(id);
    }

    public Doctor findDoctorByUsername(String username) {
        return doctorRepository.findByUsername(username);
    }
    @Transactional
    public void deleteDoctorByUsername(String username)
    {
        Optional<Doctor> prosumerOptional = Optional.ofNullable(doctorRepository.findByUsername(username));
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with username{} was not found in db", username);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + username+" was not found!");
        }
        doctorRepository.deleteByUsername(username);
    }

    public DoctorDTO transferData(Doctor doctor){
        return DoctorBuilder.toDoctorDTO(doctor);
    }
}
