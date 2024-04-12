package com.gsb_appart.gsb_appart.Controller;

import com.gsb_appart.gsb_appart.Model.Demandeurs.Demande;
import com.gsb_appart.gsb_appart.Services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConnexionController {

    private final DemandeService demandeService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ConnexionController(DemandeService demandeService, PasswordEncoder passwordEncoder) {
        this.demandeService = demandeService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "Connexion";
    }



    public String loginSubmit(@RequestParam String loginOrEmail, @RequestParam String password) {
        System.out.println("Tentative de connexion avec : " + loginOrEmail); //rien ne s'affiche je meurs 3h30
        Demande demande = demandeService.getDemandeByEmail(loginOrEmail);
        if (demande == null) {
            demande = demandeService.getDemandeByLogin(loginOrEmail);
        }

        if (demande != null && passwordEncoder.matches(password, demande.getMdp())) {
            System.out.println("Authentification réussie pour : " + loginOrEmail);
            return "redirect:/profil";
        } else {
            System.out.println("Authentification échouée pour : " + loginOrEmail); // Ajouter pour le débogage
            return "redirect:/login?error";
        }
    }

}
