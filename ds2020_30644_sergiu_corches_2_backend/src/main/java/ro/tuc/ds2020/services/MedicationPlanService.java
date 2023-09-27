package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.builders.MedicationPlanBuilder;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.repositories.MedicationPlanRepository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicationPlanService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicationPlanService.class);
    private final MedicationPlanRepository medicationPlanRepository;

    @Autowired
    public MedicationPlanService(MedicationPlanRepository medicationPlanRepository) {
        this.medicationPlanRepository = medicationPlanRepository;
    }

    public List<MedicationPlanDTO> findMedicationPlan() {
        List<MedicationPlan> medicationsList = medicationPlanRepository.findAll();
        return medicationsList.stream()
                .map(MedicationPlanBuilder::toMedicationPlanDTO)
                .collect(Collectors.toList());
    }

    public MedicationPlanDTO findMedicationPlanById(UUID id) {
        Optional<MedicationPlan> prosumerOptional = medicationPlanRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("MedicationPlan with id {} was not found in db", id);
            throw new ResourceNotFoundException(MedicationPlan.class.getSimpleName() + " with id: " + id);
        }
        return MedicationPlanBuilder.toMedicationPlanDTO(prosumerOptional.get());
    }

    @Transactional
    public UUID insert(MedicationPlanDTO medicationPlanDTO) {
        MedicationPlan medicationPlan = MedicationPlanBuilder.toEntity(medicationPlanDTO);
        medicationPlan = medicationPlanRepository.save(medicationPlan);
        LOGGER.debug("Medication with id {} was inserted in db", medicationPlan.getId());
        return medicationPlan.getId();
    }

    public MedicationPlan insertRetObj(MedicationPlanDTO medicationPlanDTO) {
        MedicationPlan medicationPlan = MedicationPlanBuilder.toEntity(medicationPlanDTO);
        medicationPlan = medicationPlanRepository.save(medicationPlan);
        LOGGER.debug("Medication with id {} was inserted in db", medicationPlan.getId());
        return medicationPlan;
    }

    public void deleteMedicationPlanById(UUID id)
    {
        Optional<MedicationPlan> prosumerOptional = medicationPlanRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Medication with id {} was not found in db", id);
            throw new ResourceNotFoundException(MedicationPlan.class.getSimpleName() + " with id: " + id+" was not found!");
        }
        medicationPlanRepository.deleteById(id);
    }

   /* public  void removeFromMedicationPlan(Optional<Medication> medicationPropriety){
        List<MedicationPlanDTO> medicationsList=this.findMedicationPlan();
        Optional<Optional<Medication>> med=Optional.ofNullable(medicationPropriety);
        if(med.isPresent()){
            String name=med.orElse(medicationPropriety.getName())
            for (int i = 0; i < medicationsList.size(); i++) {
                List<Medication> lista = medicationsList.get(i).getMedicationList();
                for (int j = 0; j < lista.size(); j++)
                    if (((lista.get(j).getName()).equals(medicationPropriety.getName())) &&
                            ((lista.get(j).getDosage()) == medicationPropriety.getDosage()) &&
                            ((lista.get(j).getSideEffects()).equals(medicationPropriety.getSideEffects())))
                        lista.remove(lista.get(j));
            }
        }

    }*/

}
