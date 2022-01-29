package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kenne.noudybaapi.enumeration.RubricType;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RubriqueResponseDTO {
    private Integer idRubrique;
    private String nom;
    private String code;
    private RubricType rubricType;
}
