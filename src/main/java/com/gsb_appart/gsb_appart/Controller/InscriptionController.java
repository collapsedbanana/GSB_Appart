package com.gsb_appart.gsb_appart.Controller;

import com.gsb_appart.gsb_appart.Model.Demandeurs.Demande;
import com.gsb_appart.gsb_appart.Model.Role.Role;
import com.gsb_appart.gsb_appart.Services.DemandeService;
import com.gsb_appart.gsb_appart.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Optional;
@Controller
@RequestMapping("/inscription")
public class InscriptionController {

    private final DemandeService demandeService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InscriptionController(DemandeService demandeService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.demandeService = demandeService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ModelAndView formulaireInscription() {
        return new ModelAndView("Inscription/Inscription", "demande", new Demande());
    }

    @PostMapping
    public ModelAndView enregistrerInscription(@ModelAttribute("demande") Demande demande) {
        ModelAndView modelAndView = new ModelAndView();

        if (demandeService.emailExists(demande.getEmail()) || demandeService.loginExist(demande.getLogin())) {
            modelAndView.addObject("error", "Un compte avec cet e-mail ou ce login existe déjà. Si vous avez oublié votre mot de passe, veuillez le réinitialiser.");
            modelAndView.setViewName("Inscription/ConfirmationInscription");
            modelAndView.addObject("demande", demande);
        } else {
            demande.setMdp(passwordEncoder.encode(demande.getMdp()));

            Optional<Role> defaultRole = roleService.getRoleByName("ROLE_USER");
            defaultRole.ifPresent(role -> demande.getRoles().add(role));

            demandeService.addDemande(demande);
            modelAndView.setViewName("redirect:/ConfirmationInscription");
        }

        return modelAndView;
    }
}
