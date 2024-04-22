/* package com.gsb_appart.gsb_appart;

import com.gsb_appart.gsb_appart.Model.Locataires.Locataire;
import com.gsb_appart.gsb_appart.Model.Proprios.Proprios;
import com.gsb_appart.gsb_appart.Model.Apparts.Appart;
import com.gsb_appart.gsb_appart.Services.LocataireService;
import com.gsb_appart.gsb_appart.Services.PropriosService;
import com.gsb_appart.gsb_appart.Services.AppartService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
public class JeuEssai {

    @Bean
    @Profile("!test")
    CommandLineRunner initDatabase(PropriosService propriosService, LocataireService locataireService, AppartService appartService) {
        return args -> {
            Proprios johnDoe = new Proprios(
                    "123 rue de John", 75001, LocalDate.parse("1990-01-01"),
                    "johndoe@example.com", "john.doe", "password123",
                    "Doe", "John", 12345678901L, 50.0f);
            johnDoe = propriosService.addProprios(johnDoe);

            Appart appartJohn = new Appart();
            appartJohn.setType_appart("T2");
            appartJohn.setPrix_loc(1500);
            appartJohn.setPrix_charge(300);
            appartJohn.setRue("123 rue de John");
            appartJohn.setArrondissement("75001");
            appartJohn.setEtage(2);
            appartJohn.setAscenseur(true);
            appartJohn.setPreavis("1 mois");
            appartJohn.setDate_libre(LocalDate.now().plusMonths(1));
            appartJohn.setLocalisation_gps("48.8566,2.3522");
            appartJohn.setMetre(45);
            appartJohn.setProprios(johnDoe);
            Appart savedAppartJohn = appartService.save(appartJohn);  // Use the generic save method

            Locataire janeDoe = new Locataire();
            janeDoe.setAppart(savedAppartJohn);  // Ensure appart is set
            Locataire savedJaneDoe = locataireService.addLocataire(janeDoe);
        };
    }
}
*/