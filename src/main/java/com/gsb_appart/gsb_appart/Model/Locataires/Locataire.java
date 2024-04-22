package com.gsb_appart.gsb_appart.Model.Locataires;

import com.gsb_appart.gsb_appart.Model.Apparts.Appart;
import com.gsb_appart.gsb_appart.Model.Users.Utilisateur;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table(name="Locataire")

public class Locataire extends Utilisateur {
    private String num_cpte_banque;
    private String num_compte;
    private String banque;
    private String rue_banque;
    private int codeville_banque;
    private long tel_banque;
    private int numappart;


    public Locataire(  String adresse, int code_ville,LocalDate date_naiss, String email,String login, String mdp, String nom,String prenom,  long tel, String banque,int codeville_banque, String num_compte, String num_cpte_banque, int numappart, String rue_banque, long tel_banque)  {
        super( adresse, code_ville, date_naiss, email, login,  mdp,  nom, prenom, tel);
        this.num_cpte_banque = num_cpte_banque;
        this.num_compte = num_compte;
        this.banque = banque;
        this.rue_banque = rue_banque;
        this.codeville_banque = codeville_banque;
        this.tel_banque = tel_banque;
        this.numappart = numappart;
    }

    public Locataire() {

    }

    @OneToOne
    @JoinColumn(name = "appart_id_appart")
    private Appart appart;
}
