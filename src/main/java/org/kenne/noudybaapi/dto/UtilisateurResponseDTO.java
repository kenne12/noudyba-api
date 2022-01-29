package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kenne.noudybaapi.domain.Role;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UtilisateurResponseDTO {

    private Integer idUtilisateur;
    private String nom;
    private String prenom;
    private String username;
    private String password;
    private String photo;
    private boolean actif;
    private Date dateCreation;
    private Collection<Role> roles;
}
