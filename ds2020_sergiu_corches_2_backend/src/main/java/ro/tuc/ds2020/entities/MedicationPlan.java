package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.Serializable;

import java.util.List;
import java.util.UUID;

@Entity
@Table (name="medication_plan")
public class MedicationPlan implements Serializable {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;

    @Column(name = "interval", nullable = false)
    private String interval;

    @Column(name = "numarZile", nullable = false)
    private int numarZile;


    @ManyToMany(cascade={CascadeType.MERGE},
            fetch=FetchType.EAGER)
    List<Medication> medicationList;

    public MedicationPlan(){}
    public MedicationPlan(UUID id, String interval, int numarZile, List<Medication> medicationList) {
        this.id = id;
        this.interval = interval;
        this.numarZile = numarZile;
        this.medicationList = medicationList;
    }

    public MedicationPlan( String interval, int numarZile, List<Medication> medicationList) {
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
