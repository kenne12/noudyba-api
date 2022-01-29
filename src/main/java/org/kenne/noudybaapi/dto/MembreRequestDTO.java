package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MembreRequestDTO {

    private Long idMembre;
    @NotEmpty(message = "Member name can not be null or empty")
    private String nom;
    private String prenom;
    private String contact;
    private boolean state;
    private int idVille;
}
