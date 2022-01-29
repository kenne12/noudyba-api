package org.kenne.noudybaapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kenne.noudybaapi.enumeration.RubricType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Rubrique {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rubrique")
    private Integer idRubrique;

    @NotEmpty(message = "Rubric Name can not be empty or null")
    private String nom;

    @NotEmpty(message = "Rubric code not be empty or null")
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(name = "rubric_type")
    @NotNull(message = "Rubric repeat payload can not be null")
    private RubricType rubricType;
}
