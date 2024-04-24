package com.gsb_appart.gsb_appart.Repository;

import com.gsb_appart.gsb_appart.Model.Demandeurs.Demande;
import com.gsb_appart.gsb_appart.Model.Proprios.Proprios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropriosRepository extends JpaRepository<Proprios, Long> {

    Optional<Proprios> findByEmail(String email);
    Optional<Proprios> findByLogin(String login);

}







