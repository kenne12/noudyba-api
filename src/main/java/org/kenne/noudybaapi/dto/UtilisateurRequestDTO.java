package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UtilisateurRequestDTO {

    private Integer idUtilisateur;
    @NotEmpty( message = "Name can not be null or empty")
    private String nom;
    private String prenom;
    @NotEmpty( message = "Username can not be null or empty")
    private String username;
    @NotEmpty( message = "Password can not be null or empty")
    private String password;
    private String photo;
    private boolean actif;
    private Date dateCreation;
}
