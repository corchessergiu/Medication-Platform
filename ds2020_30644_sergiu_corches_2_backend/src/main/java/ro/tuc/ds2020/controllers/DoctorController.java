package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.CaregiverDTO;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.services.DoctorService;

import javax.print.Doc;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/doctor")
public class DoctorController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping()
    public ResponseEntity<List<DoctorDTO>> getPatients() {
        List<DoctorDTO> dtos = doctorService.findDoctor();
        for (DoctorDTO dto : dtos) {
            Link personLink = linkTo(methodOn(DoctorController.class)
                    .getDoctor(dto.getId())).withRel("patientDetails");
            dto.add(personLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody DoctorDTO doctorDTO) {
        UUID doctorID = doctorService.insert(doctorDTO);

        return new ResponseEntity<>(doctorID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable("id") UUID doctorID) {
        DoctorDTO dto = doctorService.findDoctorById(doctorID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public  void deleteDoctor(@PathVariable("id") UUID patientId) {
        doctorService.deleteDoctorById(patientId);
    }


    @PutMapping(value="/{id}")
    public @ResponseBody void updateDoctor(@PathVariable("id")UUID  idDoctor,@Valid @RequestBody DoctorDTO doctorDTO){
        DoctorDTO doctorById= doctorService.findDoctorById(idDoctor);
        String username=doctorById.getUsername();
        Doctor doctor=doctorService.findDoctorByUsername(username);
        doctorService.deleteDoctorByUsername(username);
        doctor.setPassword(bCryptPasswordEncoder.encode(doctorDTO.getPassword()));
        doctor.setUsername(doctorDTO.getUsername());
        doctor.setNameUser(doctorDTO.getNameUser());
        doctor.setGender(doctorDTO.getGender());
        doctor.setBirth_date(doctorDTO.getBirth_date());
        doctor.setAdress(doctorDTO.getAdress());
        DoctorDTO doctorDTO1= doctorService.transferData(doctor);
        doctorService.insert(doctorDTO1);
    }


    //TODO: UPDATE, DELETE per resource

}
