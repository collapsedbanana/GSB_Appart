package com.gsb_appart.gsb_appart.Repository;

import com.gsb_appart.gsb_appart.Model.Demandeurs.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface DemandeRepository extends JpaRepository<Demande, Long> {
    Optional<Demande> findByEmail(String email);
    Optional<Demande> findByLogin(String login);

}



