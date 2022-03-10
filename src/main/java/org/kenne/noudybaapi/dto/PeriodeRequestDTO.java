package org.kenne.noudybaapi.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class PeriodeRequestDTO {


    private Integer idAnnee;

    @NotNull
    private Integer idMois;

    @NotNull
    private String shortName;
    @NotNull
    private Integer numero;
    @NotNull
    private Date dateDebut;
    @NotNull
    private Date dateFin;
}
