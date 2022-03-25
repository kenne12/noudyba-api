package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class PosteRequestDTO {
    private Integer idPoste;
    private String code;
    @NotEmpty(message = "Name can not be null or empty")
    private String nom;

    public PosteRequestDTO(Integer idPoste) {
        this.idPoste = idPoste;
    }
}
