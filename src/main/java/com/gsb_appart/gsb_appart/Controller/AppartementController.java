package com.gsb_appart.gsb_appart.Controller;

import com.gsb_appart.gsb_appart.Model.Apparts.Appart;
import com.gsb_appart.gsb_appart.Services.AppartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/appartements")
public class AppartementController {

    private final AppartService appartService;

    @Autowired
    public AppartementController(AppartService appartService) {
        this.appartService = appartService;
    }


    @GetMapping("/lister")
    public String listerAppartements(Model model) {
        model.addAttribute("appartements", appartService.getAllApparts());
        return "ModiferAppart";
    }


    @GetMapping("/modifier/{id}")
    public ModelAndView afficherFormulaireModification(@PathVariable("id") Long id) {
        Appart appart = appartService.getAppartById(id).orElse(null);
        ModelAndView modelAndView = new ModelAndView("modifier_appart");
        modelAndView.addObject("appart", appart);
        return modelAndView;
    }


    @PostMapping("/modifier/{id}")
    public String modifierAppart(Appart appart, @PathVariable("id") Long id) {
        appartService.updateAppart(id, appart);
        return "redirect:/appartements/lister";
    }


    @GetMapping("/supprimer/{id}")
    public String supprimerAppart(@PathVariable("id") Long id) {
        appartService.deleteAppart(id);
        return "redirect:/appartements/lister";
    }
}
