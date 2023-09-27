package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.MedicationPlan;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface MedicationPlanRepository extends JpaRepository<MedicationPlan, UUID> {

    Optional<MedicationPlan> findById(UUID id);
}
