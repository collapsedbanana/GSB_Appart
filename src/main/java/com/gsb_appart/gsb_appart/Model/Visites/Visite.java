package com.gsb_appart.gsb_appart.Model.Visites;

import com.gsb_appart.gsb_appart.Model.Apparts.Appart;
import com.gsb_appart.gsb_appart.Model.Demandeurs.Demande;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Visiter")
public class Visite {
    @EmbeddedId
    private VisiterId id;

    @MapsId("iduser")
    @ManyToOne
    @JoinColumn(name = "iduser", insertable = false, updatable = false)
    private Demande demande;

    @MapsId("idappart")
    @ManyToOne
    @JoinColumn(name = "idappart", insertable = false, updatable = false)
    private Appart appart;

    private LocalDate dateVisite;
}
