package ro.tuc.ds2020.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.services.MedicationService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/medication")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping()
    public ResponseEntity<List<MedicationDTO>> getMedicaions() {
        List<MedicationDTO> dtos = medicationService.findMedication();
        for (MedicationDTO dto : dtos) {
            Link medicationLink = linkTo(methodOn(MedicationController.class)
                    .getMedication(dto.getId())).withRel("medicationDetails");
            dto.add(medicationLink);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody MedicationDTO medicationDTO) {
        UUID medicationID = medicationService.insert(medicationDTO);
        return new ResponseEntity<>(medicationID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationDTO> getMedication(@PathVariable("id") UUID medicationID) {
        MedicationDTO dto = medicationService.findMedicationById(medicationID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public  void deleteMedication(@PathVariable("id") UUID medicationID) {
        medicationService.deleteMedicationById(medicationID);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UUID> updateMedication(@PathVariable("id") UUID medicationID,@RequestBody MedicationDTO medicationDTO){
        MedicationDTO dto = medicationService.findMedicationById(medicationID);
        dto.setName(medicationDTO.getName());
        dto.setDosage(medicationDTO.getDosage());
        dto.setSideEffects(medicationDTO.getSideEffects());
        UUID newMedicationID = medicationService.insert(dto);
        return new ResponseEntity<>(medicationID, HttpStatus.CREATED);
    }

    //TODO: UPDATE, DELETE per resource

}
