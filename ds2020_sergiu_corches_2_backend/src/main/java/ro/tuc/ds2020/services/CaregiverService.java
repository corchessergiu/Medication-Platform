package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.builders.CaregiverBuilder;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.CaregiverRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CaregiverService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CaregiverService.class);
    private final CaregiverRepository caregiverRepository;
    private final PatientRepository patientRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    public CaregiverService(CaregiverRepository caregiverRepository, PatientRepository patientRepository) {
        this.caregiverRepository = caregiverRepository;
        this.patientRepository = patientRepository;
    }

    public List<CaregiverDTO> findCaregiver() {
        List<Caregiver> caregiversList = caregiverRepository.findAll();
        return caregiversList.stream()
                .map(CaregiverBuilder::toCaregiverDTO)
                .collect(Collectors.toList());
    }
    public List<PatientDTO> findCaregiverPatient(UUID id){
        List<Caregiver> caregivers=new ArrayList<>();
        caregivers=caregiverRepository.findAll();
        for(int i=0;i<caregivers.size();i++)
            if(caregivers.get(i).getId().equals(id)){
                List<Patient> patients=new ArrayList<>();
                patients= caregivers.get(i).getPatients();
                List<PatientDTO> patientDTOS=new ArrayList<>();
                for (int j=0;j<patients.size();j++) {
                    PatientDTO patientDTO = PatientBuilder.toPatientDTO(patients.get(j));
                    patientDTOS.add(patientDTO);
                }
                return patientDTOS;
            }
        return  null;
    }
    public CaregiverDTO findCaregiverById(UUID id) {
        Optional<Caregiver> prosumerOptional = caregiverRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db", id);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + id);
        }
        return CaregiverBuilder.toCaregiverDTO(prosumerOptional.get());
    }

    @Transactional
    public UUID insert(CaregiverDTO caregiverDTO) {
        Caregiver caregiver = CaregiverBuilder.toEntity(caregiverDTO);
        caregiver.setPassword(bCryptPasswordEncoder.encode(caregiverDTO.getPassword()));
        caregiver = caregiverRepository.save(caregiver);
        LOGGER.debug("Caregiver with id {} was inserted in db", caregiver.getId());
        return caregiver.getId();
    }
    public UUID insertUPP(CaregiverDTO caregiverDTO) {
        Caregiver caregiver = CaregiverBuilder.toEntity(caregiverDTO);
        caregiver = caregiverRepository.save(caregiver);
        return caregiver.getId();
    }

    public void deleteCaregiverById(UUID id)
    {
        Optional<Caregiver> prosumerOptional = caregiverRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caregiver with id {} was not found in db", id);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + id+" was not found!");
        }
        caregiverRepository.deleteById(id);
    }

    public Caregiver findCaregiverByUsername(String username) {
        return caregiverRepository.findByUsername(username);
    }
    @Transactional
    public void deleteCaregiverByUsername(String username)
    {
        Optional<Caregiver> prosumerOptional = Optional.ofNullable(caregiverRepository.findByUsername(username));
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with username{} was not found in db", username);
            throw new ResourceNotFoundException(Caregiver.class.getSimpleName() + " with id: " + username+" was not found!");
        }
        caregiverRepository.deleteByUsername(username);
    }

    public CaregiverDTO transferData(Caregiver caregiver){
        return CaregiverBuilder.toCaregiverDTO(caregiver);
    }
}
