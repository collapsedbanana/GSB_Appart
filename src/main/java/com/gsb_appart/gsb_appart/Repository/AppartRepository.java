package com.gsb_appart.gsb_appart.Repository;

import com.gsb_appart.gsb_appart.Model.Apparts.Appart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppartRepository extends JpaRepository<Appart, Long> {

    List<Appart> findByProprios_IdUser(Long id); // This follows Java conventions

}
