package com.gsb_appart.gsb_appart.Controller;

import com.gsb_appart.gsb_appart.Services.AppartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfilController {

    @Autowired
    private AppartService appartService;

    @GetMapping("/profil")
    public String profil(Model model) {
        model.addAttribute("appartements", appartService.getAllApparts());
        return "Profil";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
