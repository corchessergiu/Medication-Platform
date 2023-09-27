package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Caregiver;
import ro.tuc.ds2020.entities.Patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface CaregiverRepository extends JpaRepository<Caregiver, UUID> {

    /**
     * Example: JPA generate Query by Field
     */
    void deleteById(UUID id);
    Caregiver findByUsername(String username);
    void deleteByUsername(String username);

}
