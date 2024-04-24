package com.gsb_appart.gsb_appart.Controller;

import com.gsb_appart.gsb_appart.Model.Locataires.Locataire;
import com.gsb_appart.gsb_appart.Security.ExtendedUserDetails;
import com.gsb_appart.gsb_appart.Services.LocataireService;
import com.gsb_appart.gsb_appart.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/locataires")
public class LocataireController {

    private final LocataireService locataireService;
    private final RoleService roleService;

    @Autowired
    public LocataireController(LocataireService locataireService, RoleService roleService) {
        this.locataireService = locataireService;
        this.roleService = roleService;
    }

    @GetMapping("/lister")
    public String listerLocataires(Model model) {
        List<Locataire> locatairesList = locataireService.getAllLocataires();
        model.addAttribute("locataires", locatairesList);
        return "locataire/liste";
    }

    @GetMapping("/ajouter")
    public String showAddForm(Model model) {
        model.addAttribute("locataire", new Locataire());
        return "locataire/formulaire";
    }

    @PostMapping("/ajouter")
    public String addOrUpdateLocataire(@ModelAttribute("locataire") Locataire locataire, BindingResult result, Authentication authentication) {
        if (result.hasErrors()) {
            return "locataire/formulaire";
        }

        ExtendedUserDetails userDetails = (ExtendedUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();

        // Cette partie est spécifique aux locataires et peut inclure des vérifications pour voir si l'utilisateur a déjà un appartement loué
        if (!locataireService.hasAppartement(userId)) {
            // Ici, on pourrait récupérer un appartement disponible ou traiter les cas où aucun appartement n'est disponible
            Optional<Appart> appartOptional = appartService.findAvailableAppart(); // Méthode hypothétique
            appartOptional.ifPresent(locataire::setAppart);
        }

        // Associez le rôle LOCATAIRE si ce n'est pas déjà fait
        if (!roleService.getRolesByUserId(userId).contains("ROLE_LOCATAIRE")) {
            roleService.addRoleToUser(userId, "ROLE_LOCATAIRE");
        }

        locataireService.save(locataire);
        return "redirect:/locataires/lister";
    }


    @GetMapping("/modifier/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Locataire locataire = locataireService.getLocataireById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid locataire Id:" + id));
        model.addAttribute("locataire", locataire);
        return "locataire/formulaire";
    }

    @PostMapping("/modifier/{id}")
    public String updateLocataire(@PathVariable("id") Long id, @ModelAttribute Locataire locataire) {
        locataireService.updateLocataire(id, locataire);
        return "redirect:/locataires/lister";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteLocataire(@PathVariable("id") Long id) {
        locataireService.deleteLocataire(id);
        return "redirect:/locataires/lister";
    }

    // Add other controller methods as needed
}
