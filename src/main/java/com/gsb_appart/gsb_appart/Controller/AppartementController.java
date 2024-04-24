package com.gsb_appart.gsb_appart.Controller;

import com.gsb_appart.gsb_appart.Model.Apparts.Appart;
import com.gsb_appart.gsb_appart.Model.Proprios.Proprios;
import com.gsb_appart.gsb_appart.Model.Role.Role;
import com.gsb_appart.gsb_appart.Security.ExtendedUserDetails;
import com.gsb_appart.gsb_appart.Services.AppartService;
import com.gsb_appart.gsb_appart.Services.PropriosService;
import com.gsb_appart.gsb_appart.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
@RequestMapping("/appartements")
public class AppartementController {

    private final AppartService appartService;
    private final PropriosService propriosService;

    private final RoleService roleService;

    @Autowired
    public AppartementController(AppartService appartService,PropriosService propriosService,RoleService roleService) {
        this.appartService = appartService;
        this.propriosService = propriosService;
        this.roleService=roleService;
    }




    @GetMapping("/lister")
    public String listerAppartements(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Check if the principal is indeed an instance of UserDetails
        if (authentication.getPrincipal() instanceof UserDetails) {
            Long userId = ((ExtendedUserDetails) authentication.getPrincipal()).getUserId();
            List<Appart> userApparts = appartService.getAppartsByPropriosId(userId);
            boolean hasApparts = !userApparts.isEmpty();

            model.addAttribute("appartements", userApparts);
            model.addAttribute("hasApparts", hasApparts);
        } else {
            // Handle cases where the principal is not of expected type
            model.addAttribute("error", "Authentication principal is not recognized.");
            return "errorPage";  // Redirect to an error page or another appropriate response
        }
        return "Appart/MesApparts";  // View name should match the actual view file in your templates directory
    }



    // Method to display the form for creating or updating an appartement
    @GetMapping("/ajouter")
    public String showUpdateOrCreateForm(Model model) {
        model.addAttribute("appart", new Appart()); // Assuming 'Appart' is your model class
        return "Appart/UpdateorCreate";  // This should be the name of the HTML file under /src/main/resources/templates (for Thymeleaf)
    }

    // Method to handle the submission of the form
    @PostMapping("/ajouter")
    public String addOrUpdateAppart(@ModelAttribute("appart") Appart appart, BindingResult result, Authentication authentication) {
        if (result.hasErrors()) {
            return "Appart/UpdateorCreate";
        }

        ExtendedUserDetails userDetails = (ExtendedUserDetails) authentication.getPrincipal();
        Long userId = userDetails.getUserId();

        Proprios proprios;
        if (!userDetails.hasApparts()) {
            proprios = propriosService.transitionDemandeToProprios(userId);
            Role roleProprietaire = roleService.getRoleByName("ROLE_PROPRIETAIRE")
                    .orElseThrow(() -> new IllegalStateException("Role PROPRIETAIRE not found"));

        } else {
            proprios = propriosService.getProprietaireById(userId)
                    .orElseThrow(() -> new UsernameNotFoundException("Proprios not found"));
        }

        appart.setProprios(proprios);
        appartService.save(appart);

        return "redirect:/appartements/lister";
    }


    @GetMapping("/supprimer/{id}")
    public String supprimerAppart(@PathVariable("id") Long id) {
        appartService.deleteAppart(id);
        return "redirect:/appartements/lister";//Redirige vers la list des appartements après la supression
    }



}
