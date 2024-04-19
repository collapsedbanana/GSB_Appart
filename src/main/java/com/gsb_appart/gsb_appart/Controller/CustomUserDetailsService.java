package com.gsb_appart.gsb_appart.Controller;

import com.gsb_appart.gsb_appart.Model.Demandeurs.Demande;
import com.gsb_appart.gsb_appart.Services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DemandeService demandeService;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Fetching user by username: " + username);
        Demande demande = demandeService.getDemandeByEmail(username);
        if (demande == null) {
            System.out.println("identifiant non trouver");
            demande = demandeService.getDemandeByLogin(username);

        }
        if (demande != null) {
            return new User(demande.getLogin(), demande.getMdp(), Collections.emptyList());
        } else {
            throw new UsernameNotFoundException("Existe pas");
        }

    }
}