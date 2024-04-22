package com.gsb_appart.gsb_appart.Model.Apparts;



import com.gsb_appart.gsb_appart.Model.Locataires.Locataire;
import com.gsb_appart.gsb_appart.Model.Photo.Photo;
import com.gsb_appart.gsb_appart.Model.Proprios.Proprios;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity

public class Appart  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_appart;

    private String type_appart;
    private int prix_loc;
    private int prix_charge;
    private String rue;
    private String arrondissement;
    private int etage;
    private boolean ascenseur;
    private String preavis;
    private LocalDate date_libre;
    private int numeroprop;
    private int numeroaloc;
    private String localisation_gps;
    private int metre;

    //Proprios
    @ManyToOne
    @JoinColumn(referencedColumnName = "id_user")
    private Proprios proprios;

    //Photo
    @OneToMany(mappedBy = "appart")
    private List<Photo> photos;
    //Locataire
    @OneToOne(mappedBy = "appart")
    private Locataire locataire;


}
