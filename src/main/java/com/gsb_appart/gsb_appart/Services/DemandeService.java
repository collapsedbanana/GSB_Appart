package com.gsb_appart.gsb_appart.Services;

import com.gsb_appart.gsb_appart.Model.Apparts.Appart;
import com.gsb_appart.gsb_appart.Model.Demandeurs.Demande;
import com.gsb_appart.gsb_appart.Model.Role.Role;
import com.gsb_appart.gsb_appart.Repository.DemandeRepository;
import com.gsb_appart.gsb_appart.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DemandeService {

    private final DemandeRepository demandeRepository;
    private final RoleRepository roleRepository; // Inject the RoleRepository

    public Demande addDemande(Demande demande) {
        return demandeRepository.save(demande);
    }

    public List<Demande> getAllDemande() {
        return demandeRepository.findAll();
    }

    public Demande getDemandeByEmail(String email) {
        return demandeRepository.findByEmail(email).orElse(null);
    }

    public boolean emailExists(String email) {
        return demandeRepository.findByEmail(email).isPresent();
    }

    public boolean loginExist(String login) {
        return demandeRepository.findByLogin(login).isPresent();
    }

    public Demande getDemandeById(Long id) {
        return demandeRepository.findById(id).orElse(null);
    }

    public Demande getDemandeByLogin(String login) {
        return demandeRepository.findByLogin(login).orElse(null);
    }

    public Demande updateDemande(Long id, Demande demandeDetail) {
        Demande demande = demandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée avec cet id"));

        demande.setNom(demandeDetail.getNom());
        demande.setPrenom(demandeDetail.getPrenom());
        demande.setAdresse(demandeDetail.getAdresse());
        demande.setCode_ville(demandeDetail.getCode_ville());
        demande.setTel(demandeDetail.getTel());
        demande.setEmail(demandeDetail.getEmail());
        demande.setDate_naiss(demandeDetail.getDate_naiss());
        demande.setLogin(demandeDetail.getLogin());
        demande.setMdp(demandeDetail.getMdp());

        return demandeRepository.save(demande);
    }

    public void deleteDemande(Long id) {
        Demande demande = demandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée avec cet id"));
        demandeRepository.delete(demande);
    }

    public Demande createNewDemande(Demande demande) {
        // Assign a default role
        Role defaultRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));
        demande.setRoles(Collections.singleton(defaultRole));
        return demandeRepository.save(demande);
    }
}
