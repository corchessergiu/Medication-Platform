package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Medication;


import java.util.Optional;
import java.util.UUID;
@Repository
public interface MedicationRepository  extends JpaRepository<Medication, UUID> {

    void deleteById(UUID id);
    Optional<Medication> findById(UUID id);
}
