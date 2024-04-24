package com.gsb_appart.gsb_appart.Repository;

import com.gsb_appart.gsb_appart.Model.Locataires.Locataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
@Repository
public interface LocataireRepository extends JpaRepository<Locataire, Long> {
    boolean existsById(Long id);
    Optional<Locataire> findByEmail(String email);
    Optional<Locataire> findByLogin(String login);
}


