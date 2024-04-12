    package com.gsb_appart.gsb_appart.Controller;

    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;

    @Controller
    public class ConfirmationController {

        @GetMapping("/ConfirmationInscription")
        public String confirmationInscription() {
            return "ConfirmationInscription";
        }
    }
