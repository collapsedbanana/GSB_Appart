package com.gsb_appart.gsb_appart.Controller;

import com.gsb_appart.gsb_appart.Model.Users.Utilisateur;
import com.gsb_appart.gsb_appart.Security.ExtendedUserDetails;
import com.gsb_appart.gsb_appart.Services.AppartService;
import com.gsb_appart.gsb_appart.Services.DemandeService;
import com.gsb_appart.gsb_appart.Services.PropriosService;
import com.gsb_appart.gsb_appart.Services.LocataireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AppartService appartService;
    private final DemandeService demandeService;
    private final PropriosService propriosService;
    private final LocataireService locataireService;

    @Autowired
    public CustomUserDetailsService(AppartService appartService, DemandeService demandeService, PropriosService propriosService, LocataireService locataireService) {
        this.appartService = appartService;
        this.demandeService = demandeService;
        this.propriosService = propriosService;
        this.locataireService = locataireService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = fetchUserDetails(username);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("User not found with username/email: " + username);
        }

        Set<GrantedAuthority> authorities = new HashSet<>(); // No roles are added for now
        boolean hasApparts = !appartService.getAppartsByPropriosId(utilisateur.getId()).isEmpty();

        return new ExtendedUserDetails(utilisateur, authorities, hasApparts);
    }

    private Utilisateur fetchUserDetails(String username) {
        // Attempt to fetch by email or login
        Utilisateur utilisateur = demandeService.getDemandeByEmail(username);
        if (utilisateur == null) {
            utilisateur = demandeService.getDemandeByLogin(username);
        }

        if (utilisateur == null) {
            utilisateur = propriosService.getProprioByEmail(username);
            if (utilisateur == null) {
                utilisateur = propriosService.getProprioByLogin(username);
            }
        }

        if (utilisateur == null) {
            utilisateur = locataireService.getLocataireByEmail(username);
            if (utilisateur == null) {
                utilisateur = locataireService.getLocataireByLogin(username);
            }
        }

        return utilisateur;
    }
}
