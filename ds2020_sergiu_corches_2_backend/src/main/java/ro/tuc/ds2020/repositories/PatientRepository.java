package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Patient;

import java.util.UUID;
@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    /**
     * Example: JPA generate Query by Field
     */
    void deleteById(UUID id);
    Patient findByUsername(String username);
    void deleteByUsername(String username);



}
