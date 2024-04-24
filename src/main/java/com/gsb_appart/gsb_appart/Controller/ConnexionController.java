package com.gsb_appart.gsb_appart.Controller;

import com.gsb_appart.gsb_appart.Services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConnexionController {

    private final DemandeService demandeService;
    private final PasswordEncoder passwordEncoder;

    private int value;


    @Autowired
    public ConnexionController(DemandeService demandeService, PasswordEncoder passwordEncoder) {
        this.demandeService = demandeService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")

    public String loginForm() {

        return "Connexion/Connexion";
    }
}
//Ancienne méthode de connexion non optimal et surtout ne fonctionne pas à cause de spring security
/*    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username, @RequestParam String password) {
        System.out.println("Tentative de connexion avec : " + username); //rien ne s'affiche je meurs 3h30
        Demande demande = demandeService.getDemandeByEmail(username);
        if (demande == null) {
            demande = demandeService.getDemandeByLogin(username);
        }

        if (demande != null && passwordEncoder.matches(password, demande.getMdp())) {
            System.out.println("Authentification réussie pour : " + username);
            return "redirect:/profil";
        } else {
            System.out.println("Authentification échouée pour : " + username);
            return "redirect:/login?error";
        }
    }

}
*/
