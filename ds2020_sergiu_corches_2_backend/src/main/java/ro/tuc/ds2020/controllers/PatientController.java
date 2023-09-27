package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.services.PatientService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/patient")
public class PatientController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final PatientService patientService;


    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> dtos = patientService.findPatient();
        for (PatientDTO dto : dtos) {
            Link personLink = linkTo(methodOn(PatientController.class)
                    .getPatient(dto.getId())).withRel("patientDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value="/null")
    public ResponseEntity<List<PatientDTO>> getPatientsWithoutMedicalRecord() {
        List<PatientDTO> dtos = patientService.findPatient();

        List<PatientDTO> dtos2 = patientService.findPatient();
        for(PatientDTO dt:dtos){
            UUID id=dt.getId();
            System.out.println(dt.getMedicationPlan());
            if(dt.getMedicationPlan()!=null)
            {
                for(int i=0;i<dtos2.size();i++){
                    if(dtos2.get(i).getId().equals(id)){
                        dtos2.remove(i);
                    }
                }
            }
        }
        return new ResponseEntity<>(dtos2, HttpStatus.OK);
    }

    @GetMapping(value="getPatient/{username}")
    public ResponseEntity<PatientDTO> getPatientByUsername(@PathVariable("username") String username) {
        Patient patient=patientService.findPatientByUsername(username);
        PatientDTO retPatient= PatientBuilder.toPatientDTO(patient);
        return new ResponseEntity<>(retPatient, HttpStatus.CREATED);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody PatientDTO patientDTO) {

        UUID patientID = patientService.insert(patientDTO);
        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable("id") UUID patientId) {
        PatientDTO dto = patientService.findPatientById(patientId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public  void deletePatientByID(@PathVariable("id") UUID  idPatient) {
        patientService.deletePatientById(idPatient);
    }

    @PutMapping(value="/{id}")
    public @ResponseBody void updatePatient(@PathVariable("id")UUID  idPatient,@Valid @RequestBody  PatientDTO patientDTO){

        PatientDTO pat= patientService.findPatientById(idPatient);
        String username=pat.getUsername();
        Patient patient=patientService.findPatientByUsername(username);
        System.out.println(pat.getPassword());
        pat.setId(idPatient);
        pat.setPassword(patient.getPassword());
        pat.setNameUser(patient.getUsername());

        pat.setNameUser(patientDTO.getNameUser());
        pat.setGender(patientDTO.getGender());
        pat.setBirth_date(patientDTO.getBirth_date());
        pat.setMedical_record(patientDTO.getMedical_record());
        pat.setAdress(patientDTO.getAdress());
        pat.setMedicationPlan(patientDTO.getMedicationPlan());
        patientService.insertUPP(pat);
    }
    //TODO: UPDATE, DELETE per resource

}
