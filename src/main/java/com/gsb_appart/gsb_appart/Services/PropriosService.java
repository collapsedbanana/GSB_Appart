package com.gsb_appart.gsb_appart.Services;

import com.gsb_appart.gsb_appart.Model.Demandeurs.Demande;
import com.gsb_appart.gsb_appart.Model.Proprios.Proprios;
import com.gsb_appart.gsb_appart.Repository.DemandeRepository;
import com.gsb_appart.gsb_appart.Repository.PropriosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropriosService {



    @Autowired
    private PropriosRepository propriosRepository;
    @Autowired
    private DemandeRepository demandeRepository;


    @PostMapping
    public Proprios addProprios(@RequestBody Proprios proprios) {
        return propriosRepository.save(proprios);
    }




    public List<Proprios> getAllProprietaires() {
        return propriosRepository.findAll();
    }

    public Optional<Proprios> getProprietaireById(Long id) {
        return propriosRepository.findById(id);
    }


    public Proprios updateProprietaire(Long id, Proprios proprietaireDetails) {
        Proprios proprietaire = propriosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propriétaire non trouvé pour cet id :: " + id));
        proprietaire.setNom(proprietaireDetails.getNom());
        proprietaire.setPrenom(proprietaireDetails.getPrenom());
        proprietaire.setAdresse(proprietaireDetails.getAdresse());
        proprietaire.setCode_ville(proprietaireDetails.getCode_ville());
        proprietaire.setTel(proprietaireDetails.getTel());
        proprietaire.setEmail(proprietaireDetails.getEmail());
        proprietaire.setDate_naiss(proprietaireDetails.getDate_naiss());
        proprietaire.setLogin(proprietaireDetails.getLogin());
        proprietaire.setMdp(proprietaireDetails.getMdp());
        proprietaire.setCotisation_GSB(proprietaireDetails.getCotisation_GSB());

        return propriosRepository.save(proprietaire);
    }

    public  Proprios save(Proprios proprios) {
        return propriosRepository.save(proprios);
    }

    public void deleteProprietaire(Long id) {
        Proprios proprietaire = propriosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propriétaire non trouvé pour cet id :: " + id));
        propriosRepository.delete(proprietaire);
    }

    public Proprios getProprioByEmail(String mail){
        return propriosRepository.findByEmail(mail).orElse(null);
    }

    public Proprios getProprioByLogin(String Login){
        return propriosRepository.findByLogin(Login).orElse(null);
    }

    @Transactional
    public Proprios transitionDemandeToProprios(Long id) {
        // Vérifier d'abord si l'utilisateur est déjà un propriétaire
        Optional<Proprios> existingProprios = propriosRepository.findById(id);
        if (existingProprios.isPresent()) {
            return existingProprios.get();  // Utilisateur déjà converti en propriétaire
        }

        // Sinon, tenter de trouver et convertir la demande
        Optional<Demande> optionalDemande = demandeRepository.findById(id);
        if (optionalDemande.isEmpty()) {
            throw new IllegalStateException("Demande not found or user already a proprios.");
        }

        Demande existingDemande = optionalDemande.get();
        Proprios newProprios = new Proprios();
        BeanUtils.copyProperties(existingDemande, newProprios);
        newProprios = propriosRepository.save(newProprios);
        demandeRepository.deleteById(id);
        return newProprios;
    }


}
