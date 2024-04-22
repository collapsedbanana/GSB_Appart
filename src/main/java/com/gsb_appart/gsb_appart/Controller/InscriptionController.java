package com.gsb_appart.gsb_appart.Controller;

import com.gsb_appart.gsb_appart.Model.Demandeurs.Demande;
import com.gsb_appart.gsb_appart.Services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/inscription") // lowercase for URL
public class InscriptionController {

    private final DemandeService demandeService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public InscriptionController(DemandeService demandeService, PasswordEncoder passwordEncoder) {
        this.demandeService = demandeService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ModelAndView formulaireInscription() {
        return new ModelAndView("Inscription/Inscription", "demande", new Demande()); // Maintain the case for the view
    }

    @PostMapping
    public ModelAndView enregistrerInscription(@ModelAttribute("demande") Demande demande) {
        ModelAndView modelAndView = new ModelAndView();

        if (demandeService.emailExists(demande.getEmail()) || demandeService.loginExist(demande.getLogin())) {
            modelAndView.addObject("error", "Un compte avec cet e-mail ou ce login existe déjà.");
            modelAndView.setViewName("Inscription/Inscription"); // Maintain the case for the view
            modelAndView.addObject("demande", demande);
        } else {
            demande.setMdp(passwordEncoder.encode(demande.getMdp()));
            demandeService.addDemande(demande);
            modelAndView.setViewName("redirect:/confirmationInscription"); // lowercase for URL
        }

        return modelAndView;
    }
}
