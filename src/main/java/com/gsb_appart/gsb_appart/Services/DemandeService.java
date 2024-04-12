package com.gsb_appart.gsb_appart.Services;

import com.gsb_appart.gsb_appart.Model.Demandeurs.Demande;
import com.gsb_appart.gsb_appart.Repository.DemandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DemandeService {

    private final DemandeRepository demanderepository;

    @PostMapping
    public Demande addDemande(@RequestBody Demande demande) {
        return demanderepository.save(demande);
    }
    public List<Demande> getAllDemande() {
        return demanderepository.findAll();
    }

    public Demande getDemandeByEmail(String email) {
        return demanderepository.findByEmail(email).orElse(null);
    }

    public boolean emailExists(String email) {
        return demanderepository.findByEmail(email).isPresent(); //
    }

    public boolean loginExist(String login) {
        return demanderepository.findByLogin(login).isPresent();
    }
    public Demande getDemandeById(Long id) {
        return demanderepository.findById(id).orElse(null);
    }

    public Demande getDemandeByLogin(String login){ return demanderepository.findByLogin(login).orElse(null);
    }
    public Demande updateDemande(Long id, Demande DemandeDetail) {
        Demande demande = demanderepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée avec cet id"));

        demande.setNom(DemandeDetail.getNom());
        demande.setPrenom(DemandeDetail.getPrenom());
        demande.setAdresse(DemandeDetail.getAdresse());
        demande.setCode_ville(DemandeDetail.getCode_ville());
        demande.setTel(DemandeDetail.getTel());
        demande.setEmail(DemandeDetail.getEmail());
        demande.setDate_naiss(DemandeDetail.getDate_naiss());
        demande.setLogin(DemandeDetail.getLogin());
        demande.setMdp(DemandeDetail.getMdp());

        return demanderepository.save(demande);
    }

    public void deleteDemande(Long id) {
        Demande demande = demanderepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée avec cet id"));
        demanderepository.delete(demande);
    }
}
