package com.gsb_appart.gsb_appart.Controller;

import com.gsb_appart.gsb_appart.Model.Demandeurs.Demande;
import com.gsb_appart.gsb_appart.Security.ExtendedUserDetails;
import com.gsb_appart.gsb_appart.Services.DemandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private DemandeService demandeService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Demande demande = demandeService.getDemandeByEmail(username);
        if (demande == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new ExtendedUserDetails(demande);
    }
}