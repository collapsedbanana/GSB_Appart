package com.gsb_appart.gsb_appart.Services;

import com.gsb_appart.gsb_appart.Model.Locataires.Locataire;
import com.gsb_appart.gsb_appart.Repository.LocataireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocataireService {

    private final LocataireRepository locataireRepository;


    @PostMapping
    public Locataire addLocataire(@RequestBody Locataire locataire) {
        return locataireRepository.save(locataire); // Correction ici : Utiliser photoRepository au lieu de PhotoRepository
    }

    public List<Locataire> getAllLocataires() {
        return locataireRepository.findAll();
    }

    public Optional<Locataire> getLocataireById(Long id) {
        return locataireRepository.findById(id);
    }

    public Locataire updateLocataire(Long id, Locataire locataireDetails) {
        Locataire locataire = locataireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Locataire non trouvé pour cet id :: " + id));
        locataire.setNum_cpte_banque(locataireDetails.getNum_cpte_banque());
        locataire.setNum_compte(locataireDetails.getNum_compte());
        locataire.setBanque(locataireDetails.getBanque());
        locataire.setRue_banque(locataireDetails.getRue_banque());
        locataire.setCodeville_banque(locataireDetails.getCodeville_banque());
        locataire.setTel_banque(locataireDetails.getTel_banque());
        locataire.setNumappart(locataireDetails.getNumappart());
        locataire.setIdAppart(locataireDetails.getIdAppart());


        return locataireRepository.save(locataire);
    }

    public void deleteLocataire(Long id) {
        Locataire locataire = locataireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Locataire non trouvé pour cet id :: " + id));
        locataireRepository.delete(locataire);
    }
}
