package com.gsb_appart.gsb_appart.Model.Proprios;

import com.gsb_appart.gsb_appart.Model.Apparts.Appart;
import com.gsb_appart.gsb_appart.Model.Users.Utilisateur;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Proprios extends Utilisateur {

    private float Cotisation_GSB;



    public Proprios(String adresse, int code_ville, LocalDate date_naiss, String email, String login, String mdp, String nom, String prenom, long tel, float Cotisation_GSB) {
        super(adresse, code_ville, date_naiss, email, login, mdp, nom, prenom, tel);
        this.Cotisation_GSB = Cotisation_GSB;
    }

    public Proprios() {
        super();
    }

    @OneToMany(mappedBy = "proprios")
    private List<Appart> apparts;
}
