package com.gsb_appart.gsb_appart.Services;

import com.gsb_appart.gsb_appart.Model.Visites.Visite;
import com.gsb_appart.gsb_appart.Repository.VisiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisiteService {

    private final VisiteRepository visiteRepository;


    @PostMapping
    public Visite addVisite(@RequestBody Visite visite) {
        return visiteRepository.save(visite); // Correction ici : Utiliser photoRepository au lieu de PhotoRepository
    }


    public List<Visite> getAllVisites() {
        return visiteRepository.findAll();
    }

    public Optional<Visite> getVisiteById(Long id) {
        return visiteRepository.findById(id);
    }

    public Visite updateVisite(Long id, Visite visiteDetails) {
        Visite visite = visiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visite non trouvée pour cet id :: " + id));
        visite.setDateVisite(visiteDetails.getDateVisite());
        // Assurez-vous de mettre à jour les autres champs si nécessaire

        // Enregistrer la visite mise à jour dans la base de données
        return visiteRepository.save(visite);
    }

    public void deleteVisite(Long id) {
        Visite visite = visiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Visite non trouvée pour cet id :: " + id));
        visiteRepository.delete(visite);
    }
}
