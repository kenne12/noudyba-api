package org.kenne.noudybaapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class BureauPK implements Serializable {

    @Column(name = "id_membre")
    @NotNull
    private Long idMembre;

    @Column(name = "id_poste")
    @NotNull
    private Integer idPoste;

    @Column(name = "id_annee")
    @NotNull
    private Integer idAnnee;
}
