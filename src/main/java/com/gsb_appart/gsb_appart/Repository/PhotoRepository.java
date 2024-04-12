package com.gsb_appart.gsb_appart.Repository;

import com.gsb_appart.gsb_appart.Model.Photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {

}