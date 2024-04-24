package com.gsb_appart.gsb_appart.Repository;

import com.gsb_appart.gsb_appart.Model.Apparts.Appart;
import com.gsb_appart.gsb_appart.Model.Demandeurs.Demande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AppartRepository extends JpaRepository<Appart, Long> {

        List<Appart> findByProprios_Id(Long id);
        List<Appart> findByLocataire_Id(Long id);
}
