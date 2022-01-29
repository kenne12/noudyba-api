package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnneeResponseDTO {

    private Integer idAnnee;

    private String nom;
    private String code;

    private Date dateDebut;
    private Date dateFin;

    private boolean etat;
    private boolean cloturee;
}
