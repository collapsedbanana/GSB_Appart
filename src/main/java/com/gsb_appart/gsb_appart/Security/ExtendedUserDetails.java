package com.gsb_appart.gsb_appart.Security;

import com.gsb_appart.gsb_appart.Model.Users.Utilisateur;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;
import java.util.Collections;

public class ExtendedUserDetails implements UserDetails {
    private final Utilisateur utilisateur;

    public ExtendedUserDetails(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList(); // Customize based on your needs
    }

    @Override
    public String getPassword() {
        return utilisateur.getMdp();
    }

    @Override
    public String getUsername() {
        return utilisateur.getLogin();
    }

    public Long getUserId() {
        return utilisateur.getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
