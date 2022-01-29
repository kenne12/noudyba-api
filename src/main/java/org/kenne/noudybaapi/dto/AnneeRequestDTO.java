package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnneeRequestDTO {

    private Integer idAnnee;
    @NotEmpty(message = "Name can not be null or empty")
    private String nom;
    @NotEmpty(message = "Code name can not be null or empty")
    private String code;

    private Date dateDebut;
    private Date dateFin;

    private boolean etat;
    private boolean cloturee;
}
