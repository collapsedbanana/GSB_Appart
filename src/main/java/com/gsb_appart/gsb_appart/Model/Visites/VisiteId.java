
package com.gsb_appart.gsb_appart.Model.Visites;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
class VisiterId implements Serializable {
    private Long iduser; // Matches column name in 'visiter' table
    private Long idappart; // Matches column name in 'visiter' table
}