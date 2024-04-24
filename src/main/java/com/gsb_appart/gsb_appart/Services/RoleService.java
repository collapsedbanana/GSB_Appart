package com.gsb_appart.gsb_appart.Services;

import com.gsb_appart.gsb_appart.Model.Role.Role;
import com.gsb_appart.gsb_appart.Model.Users.Utilisateur;
import com.gsb_appart.gsb_appart.Repository.RoleRepository;
import com.gsb_appart.gsb_appart.Repository.UtilisateurRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, UtilisateurRepository utilisateurRepository) {
        this.roleRepository = roleRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @PostConstruct
    public void initRoles() {
        // Assurez-vous que les rôles par défaut existent dans la base de données
        if (roleRepository.findByName("ROLE_USER").isEmpty()) {
            saveRole(new Role("ROLE_USER"));
        }
        if (roleRepository.findByName("ROLE_PROPRIETAIRE").isEmpty()) {
            saveRole(new Role("ROLE_PROPRIETAIRE"));
        }
        // Ajoutez ici d'autres rôles si nécessaire
    }

    @Transactional
    public void addRoleToUser(Long userId, String roleName) {
        Utilisateur utilisateur = utilisateurRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalStateException("Role not found"));

        utilisateur.getRoles().add(role);
        utilisateurRepository.save(utilisateur);
    }
}
