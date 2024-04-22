package com.gsb_appart.gsb_appart.Model.Users;

import com.gsb_appart.gsb_appart.Model.Role.Role;
import java.time.LocalDate;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
public abstract class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;
    private String prenom;
    private String adresse;
    private int code_ville;
    private long tel;
    private String email;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate date_naiss;

    private String login;
    private String mdp;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public Utilisateur(String adresse, int code_ville, LocalDate date_naiss, String email, String login, String mdp, String nom, String prenom, long tel) {
        this.adresse = adresse;
        this.code_ville = code_ville;
        this.nom = nom;
        this.prenom = prenom;
        this.tel = tel;
        this.email = email;
        this.date_naiss = date_naiss;
        this.login = login;
        this.mdp = mdp;
    }

    //toute façon pas instancier
}
