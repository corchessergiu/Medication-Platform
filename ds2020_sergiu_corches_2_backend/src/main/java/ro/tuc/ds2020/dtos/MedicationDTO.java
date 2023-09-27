package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.Link;

import java.util.UUID;

public class MedicationDTO {

    private UUID id;
    private String name;
    private String sideEffects;
    private int dosage;

    public MedicationDTO(){}
    public MedicationDTO(UUID id, String name, String sideEffects, int dosage) {
        this.id = id;
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
    }

    public MedicationDTO( String name, String sideEffects, int dosage) {
        this.name = name;
        this.sideEffects = sideEffects;
        this.dosage = dosage;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSideEffects() {
        return sideEffects;
    }

    public int getDosage() {
        return dosage;
    }

    public void add(Link personLink) {
    }
}
