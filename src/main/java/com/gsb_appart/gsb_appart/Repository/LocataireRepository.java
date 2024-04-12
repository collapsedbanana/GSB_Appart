package com.gsb_appart.gsb_appart.Repository;

import com.gsb_appart.gsb_appart.Model.Locataires.Locataire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocataireRepository  extends JpaRepository<Locataire, Long> {
}
