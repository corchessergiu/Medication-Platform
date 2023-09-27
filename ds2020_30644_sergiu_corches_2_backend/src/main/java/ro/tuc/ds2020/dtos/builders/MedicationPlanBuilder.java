package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MedicationPlanDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.entities.MedicationPlan;
import ro.tuc.ds2020.entities.Patient;


public class MedicationPlanBuilder {

    private MedicationPlanBuilder() {
    }

    public static MedicationPlanDTO toMedicationPlanDTO(MedicationPlan medicationPlan) {
        return new MedicationPlanDTO(medicationPlan.getId(),
                medicationPlan.getInterval(),medicationPlan.getNumarZile(),medicationPlan.getMedicationList()
        );
    }


    public static MedicationPlan toEntity(MedicationPlanDTO medicationPlanDTO) {
        return new MedicationPlan(medicationPlanDTO.getId(),medicationPlanDTO.getInterval(),medicationPlanDTO.getNumarZile(),
                medicationPlanDTO.getMedicationList());
    }
}
