package com.gsb_appart.gsb_appart.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Correct import for the Spring Model
import org.springframework.web.bind.annotation.GetMapping;
import com.gsb_appart.gsb_appart.Services.AppartService;

@Controller
public class HomeController {

    @Autowired
    private AppartService appartService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("appartements", appartService.getAllApparts());
        return "home";
    }

}