package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VilleRequestDTO {
    private Integer idVille;
    @NotEmpty( message = "Name can not be null or empty")
    private String nom;
}
