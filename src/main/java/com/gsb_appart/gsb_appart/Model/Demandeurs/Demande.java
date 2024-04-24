package com.gsb_appart.gsb_appart.Model.Demandeurs;

import java.time.LocalDate;
import java.util.Collection;


import com.gsb_appart.gsb_appart.Model.Users.Utilisateur;
import com.gsb_appart.gsb_appart.Model.Visites.Visite;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter

public class Demande extends Utilisateur {


    public Demande() {
        super();
    }
    public Demande(  String adresse, int code_ville,LocalDate date_naiss, String email,String login, String mdp, String nom,String prenom,  long tel)  {
        super( adresse, code_ville, date_naiss, email, login,  mdp,  nom, prenom, tel);
    }



}

