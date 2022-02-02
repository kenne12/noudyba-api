package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MembreResponseDTO {

    private Long idMembre;
    private String code;
    private String nom;
    private String prenom;
    private String contact;
    private String photo;
    private boolean state;

    private VilleResponseDTO ville;
}
