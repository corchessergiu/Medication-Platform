package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.PatientRepository;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientDTO> findPatient() {
        List<Patient> patientList = patientRepository.findAll();
        return patientList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }



    @Transactional
    public PatientDTO findPatientById(UUID id) {
        Optional<Patient> prosumerOptional = patientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found in db", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id);
        }
        return PatientBuilder.toPatientDTO(prosumerOptional.get());
    }

    @Transactional
    public UUID insert(PatientDTO patientDTO) {
        Patient patient = PatientBuilder.toEntity(patientDTO);
        patient.setPassword(bCryptPasswordEncoder.encode(patientDTO.getPassword()));
        patient = patientRepository.save(patient);

        LOGGER.debug("Patient with id {} was inserted in db", patient.getId());
        return patient.getId();
    }

    public UUID insertUPP(PatientDTO patientDTO) {
        Patient patient = PatientBuilder.toEntity(patientDTO);
        patient = patientRepository.save(patient);
        LOGGER.debug("Patient with id {} was inserted in db", patient.getId());
        return patient.getId();
    }

    public PatientDTO transferData(Patient patient){
        return PatientBuilder.toPatientDTO(patient);
    }
    public Patient findPatientByUsername(String username) {
        return patientRepository.findByUsername(username);
    }


    public void deletePatientById(UUID id)
    {
        Optional<Patient> prosumerOptional = patientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found in db", id);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + id+" was not found!");
        }
        patientRepository.deleteById(id);
    }

    public Patient updatePatient(Patient patient){
        return patientRepository.save(patient);
    }

    @Transactional
    public void deletePatientByUsername(String username)
    {
        Optional<Patient> prosumerOptional = Optional.ofNullable(patientRepository.findByUsername(username));
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with username{} was not found in db", username);
            throw new ResourceNotFoundException(Patient.class.getSimpleName() + " with id: " + username+" was not found!");
        }
        patientRepository.deleteByUsername(username);
    }

}
