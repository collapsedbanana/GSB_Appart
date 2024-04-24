package com.gsb_appart.gsb_appart.Model.Role;

import com.gsb_appart.gsb_appart.Model.Users.Utilisateur;
import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    public Role() {

    }
    public Role(String name) {
        this.name = name;
    }
    @ManyToMany(mappedBy = "roles")
    private Set<Utilisateur> users;

}
