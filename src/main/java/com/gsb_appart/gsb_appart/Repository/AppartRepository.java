package com.gsb_appart.gsb_appart.Repository;

import com.gsb_appart.gsb_appart.Model.Apparts.Appart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppartRepository extends JpaRepository<Appart, Long> {

}
