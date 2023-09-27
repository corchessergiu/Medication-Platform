package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.*;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.services.CaregiverService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/caregiver")
public class CaregiverController {

    private final CaregiverService caregiverService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CaregiverController(CaregiverService caregiverService) {
        this.caregiverService = caregiverService;
    }

    @GetMapping()
    public ResponseEntity<List<CaregiverDTO>> getCaregivers() {
        List<CaregiverDTO> dtos = caregiverService.findCaregiver();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody CaregiverDTO caregiverDTO) {
        UUID caregiverID = caregiverService.insert(caregiverDTO);
        return new ResponseEntity<>(caregiverID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CaregiverDTO> getCaregiver(@PathVariable("id") UUID caregiversID) {
        CaregiverDTO dto = caregiverService.findCaregiverById(caregiversID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/caregiverPatients")
    public ResponseEntity<List<PatientDTO>> getCaregiverPatients(@PathVariable("id") UUID caregiversID) {
        List<PatientDTO> lista=new ArrayList<>();
        lista = caregiverService.findCaregiverPatient(caregiversID);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public  void deleteCaregiver(@PathVariable("id") UUID caregiverID) {
        caregiverService.deleteCaregiverById(caregiverID);
    }

    @PutMapping(value="/{id}")
    public @ResponseBody void updateCaregiver(@PathVariable("id")UUID  idCaregiver,@Valid @RequestBody  CaregiverDTO caregiverDTO){
        CaregiverDTO caregiverById= caregiverService.findCaregiverById(idCaregiver);
        caregiverById.setNameUser(caregiverDTO.getNameUser());
        caregiverById.setGender(caregiverDTO.getGender());
        caregiverById.setBirth_date(caregiverDTO.getBirth_date());
        caregiverById.setAdress(caregiverDTO.getAdress());
        caregiverService.insert(caregiverById);
    }
    @PutMapping(value="/addPatients/{id}")
    public @ResponseBody void updateCaregiverListOfPatient(@PathVariable("id")UUID  idCaregiver,@Valid @RequestBody  CaregiverDTO caregiverDTO){
        CaregiverDTO caregiverById= caregiverService.findCaregiverById(idCaregiver);
        String username=caregiverById.getUsername();

        System.out.println(caregiverById.getUsername());
        System.out.println(caregiverById.getPassword());

        Caregiver caregiver=caregiverService.findCaregiverByUsername(username);
        System.out.println("DE AICI!");
        System.out.println(caregiver.getUsername());
        System.out.println(caregiver.getPassword());
        caregiverById.setId(idCaregiver);
        caregiverById.setUsername(caregiver.getUsername());
        caregiverById.setPassword(caregiver.getPassword());
        System.out.println("DE AICIa!");
        System.out.println(caregiverById.getUsername());
        System.out.println(caregiverById.getPassword());


        caregiverById.setNameUser(caregiverDTO.getNameUser());
        caregiverById.setGender(caregiverDTO.getGender());
        caregiverById.setBirth_date(caregiverDTO.getBirth_date());
        caregiverById.setAdress(caregiverDTO.getAdress());
        List<PatientDTO> listaPacienti=caregiverById.getPatients();
        List<PatientDTO> listaPacientiNoi=caregiverDTO.getPatients();
            for(int i=0;i<listaPacientiNoi.size();i++){
                listaPacienti.add(listaPacientiNoi.get(i));
            }
        caregiverById.setPatients(listaPacienti);
        caregiverService.insertUPP(caregiverById);
    }

    //TODO: UPDATE, DELETE per resource

}
