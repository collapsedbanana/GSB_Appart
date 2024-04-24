package com.gsb_appart.gsb_appart.Model.Visites;

import com.gsb_appart.gsb_appart.Model.Apparts.Appart;
import com.gsb_appart.gsb_appart.Model.Locataires.Locataire;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Visiter")
public class Visite {
    @EmbeddedId
    private VisiterId id;

    @MapsId("iduser")
    @ManyToOne
    @JoinColumn(name = "iduser", insertable = false, updatable = false)
    private Locataire locataire;

    @MapsId("idappart")
    @ManyToOne
    @JoinColumn(name = "idappart", insertable = false, updatable = false)
    private Appart appart;

    private LocalDate dateVisite;
}
