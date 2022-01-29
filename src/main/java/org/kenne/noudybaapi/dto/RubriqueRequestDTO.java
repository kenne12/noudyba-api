package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kenne.noudybaapi.enumeration.RubricType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RubriqueRequestDTO {
    private Integer idRubrique;
    @NotEmpty(message = "Name can not be null or empty")
    private String nom;
    private String code;
    @NotNull(message = "Rubric repeat payload can not be null")
    private RubricType rubricType;
}
