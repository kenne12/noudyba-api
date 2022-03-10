package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UtilisateurRequestDTO {

    private Integer idUtilisateur;
    @NotEmpty(message = "Name can not be null or empty")
    private String nom;
    private String prenom;
    @NotEmpty(message = "Username can not be null or empty")
    private String username;
    @NotEmpty(message = "Password can not be null or empty")
    private String password;
    private boolean actif;
    @NotEmpty(message = "Confirmation password can not be null or empty")
    private String repeatPassword;
}
