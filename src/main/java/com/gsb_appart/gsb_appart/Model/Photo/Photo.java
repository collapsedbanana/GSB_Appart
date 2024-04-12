package com.gsb_appart.gsb_appart.Model.Photo;


import com.gsb_appart.gsb_appart.Model.Apparts.Appart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "id_appart")
    private Appart appart;
}


