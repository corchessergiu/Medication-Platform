package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.entities.UserData;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserDataRepository extends JpaRepository<UserData, UUID> {

    /**
     * Example: JPA generate Query by Field
     */
    void deleteById(UUID id);
    UserData findByUsername(String username);
    void deleteByUsername(String username);
    Optional<UserData> findById(UUID id);



}
