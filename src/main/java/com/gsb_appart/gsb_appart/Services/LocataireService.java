 package com.gsb_appart.gsb_appart.Services;

import com.gsb_appart.gsb_appart.Model.Apparts.Appart;
import com.gsb_appart.gsb_appart.Model.Locataires.Locataire;
import com.gsb_appart.gsb_appart.Repository.AppartRepository;
import com.gsb_appart.gsb_appart.Repository.LocataireRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocataireService {

    private final LocataireRepository locataireRepository;
    private final AppartRepository appartRepository;

    @Transactional
    public Locataire addLocataire(Locataire locataire) {
        if (locataire.getAppart() == null || locataire.getAppart().getId_appart() == null) {
            throw new IllegalStateException("Un locataire doit avoir un appartement assigné.");
        }

        Appart appart = appartRepository.findById(locataire.getAppart().getId_appart())
                .orElseThrow(() -> new RuntimeException("Appartement non trouvé pour cet id :: " + locataire.getAppart().getId_appart()));

        if (appart.getLocataire() != null) {
            throw new IllegalStateException("L'appartement est déjà assigné à un autre locataire.");
        }


        locataire.setAppart(appart);
        appart.setLocataire(locataire);

        Locataire savedLocataire = locataireRepository.save(locataire);
        appartRepository.save(appart);

        return savedLocataire;
    }


    public Locataire assignLocataireToAppart(Locataire locataire, Long appartId) {
        Appart appart = appartRepository.findById(appartId).orElseThrow(() -> new RuntimeException("Appartement non trouvé"));
        if (appart.getLocataire() != null) {
            throw new IllegalStateException("Cet appartement a déjà un locataire.");
        }
        locataire.setAppart(appart);
        return locataireRepository.save(locataire);
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
        locataire.setId(locataireDetails.getId());


        return locataireRepository.save(locataire);
    }

    public void deleteLocataire(Long id) {
        Locataire locataire = locataireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Locataire non trouvé pour cet id :: " + id));
        locataireRepository.delete(locataire);
    }

    public boolean isUserALocataire(Long id){
        return locataireRepository.existsById(id);
    }

    public Locataire getLocataireByEmail(String email) {
        Optional<Locataire> locataire = locataireRepository.findByEmail(email);
        return locataire.orElse(null);  // Return null or another appropriate action if Locataire is not found
    }

    public Locataire getLocataireByLogin(String login) {
        Optional<Locataire> locataire = locataireRepository.findByLogin(login);
        return locataire.orElse(null);  // Same handling as above
    }
}
