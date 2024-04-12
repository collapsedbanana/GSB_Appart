package com.gsb_appart.gsb_appart.Services;

import com.gsb_appart.gsb_appart.Model.Proprios.Proprios;
import com.gsb_appart.gsb_appart.Repository.PropriosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PropriosService {

    private final PropriosRepository propriosRepository;


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

    public void deleteProprietaire(Long id) {
        Proprios proprietaire = propriosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Propriétaire non trouvé pour cet id :: " + id));
        propriosRepository.delete(proprietaire);
    }
}
