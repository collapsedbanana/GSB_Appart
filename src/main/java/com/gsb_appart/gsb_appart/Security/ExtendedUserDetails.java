package com.gsb_appart.gsb_appart.Security;

import com.gsb_appart.gsb_appart.Model.Users.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

public class ExtendedUserDetails implements UserDetails {
    private final Utilisateur utilisateur;
    private final Collection<? extends GrantedAuthority> authorities;
    private boolean hasApparts;

    public ExtendedUserDetails(Utilisateur utilisateur, Collection<? extends GrantedAuthority> authorities, boolean hasApparts) {
        this.utilisateur = utilisateur;
        this.authorities = authorities;
        this.hasApparts = hasApparts;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities; // Now returns the actual authorities
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

    // Standard boolean access methods follow Java naming conventions
    public boolean hasApparts() {
        return hasApparts;
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
