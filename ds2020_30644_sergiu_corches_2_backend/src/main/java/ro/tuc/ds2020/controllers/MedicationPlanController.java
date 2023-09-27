package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.services.MedicationPlanService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin
@RequestMapping(value = "/medicationPlan")
public class MedicationPlanController {

    private final MedicationPlanService medicationPlanService;

    @Autowired
    public MedicationPlanController(MedicationPlanService medicationPlanService) {
        this.medicationPlanService = medicationPlanService;
    }

    @GetMapping()
    public ResponseEntity<List<MedicationPlanDTO>> getMedicaionPlan() {
        List<MedicationPlanDTO> dtos = medicationPlanService.findMedicationPlan();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody MedicationPlanDTO medicationPlanDTO) {
        UUID medicationPlanID = medicationPlanService.insert(medicationPlanDTO);
        return new ResponseEntity<>(medicationPlanID, HttpStatus.CREATED);
    }

    @PostMapping(value = "/obj")
    public ResponseEntity<MedicationPlan> insertProsumerRetObj(@Valid @RequestBody MedicationPlanDTO medicationPlanDTO) {
        MedicationPlan medicationPlan= medicationPlanService.insertRetObj(medicationPlanDTO);
        return new ResponseEntity<>(medicationPlan, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationPlanDTO> getMedicationPlan(@PathVariable("id") UUID medicationPlanID) {
        MedicationPlanDTO dto = medicationPlanService.findMedicationPlanById(medicationPlanID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public  void deleteMedicationPlan(@PathVariable("id") UUID medicationPlanId) {
        medicationPlanService.deleteMedicationPlanById(medicationPlanId);
    }

    //TODO: UPDATE, DELETE per resource

}
