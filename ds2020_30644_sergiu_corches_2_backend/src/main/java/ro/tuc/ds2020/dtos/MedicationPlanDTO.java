package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.entities.Medication;

import java.util.List;
import java.util.UUID;

public class MedicationPlanDTO {

    private UUID id;
    private String interval;
    private int numarZile;
    List<Medication> medicationList;

    public MedicationPlanDTO(){}

    public MedicationPlanDTO(UUID id, String interval, int numarZile, List<Medication> medicationList) {
        this.id = id;
        this.interval = interval;
        this.numarZile = numarZile;
        this.medicationList = medicationList;
    }

    public MedicationPlanDTO( String interval, int numarZile, List<Medication> medicationList) {
        this.interval = interval;
        this.numarZile = numarZile;
        this.medicationList = medicationList;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public void setNumarZile(int numarZile) {
        this.numarZile = numarZile;
    }

    public void setMedicationList(List<Medication> medicationList) {
        this.medicationList = medicationList;
    }

    public UUID getId() {
        return id;
    }

    public String getInterval() {
        return interval;
    }

    public int getNumarZile() {
        return numarZile;
    }

    public List<Medication> getMedicationList() {
        return medicationList;
    }
}
