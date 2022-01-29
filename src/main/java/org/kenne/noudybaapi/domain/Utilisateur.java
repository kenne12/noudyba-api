package org.kenne.noudybaapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Utilisateur {

    @Id
    @Column(name = "id_utilisateur")
    private Integer idUtilisateur;

    @Column(length = 100, nullable = false)
    @NotEmpty(message = "Name can not be empty or null")
    private String nom;

    @Column(length = 100)
    private String prenom;

    @Column(length = 100, name = "login")
    @NotEmpty(message = "Username can not be empty or null")
    private String username;

    @Column(length = 300)
    @NotEmpty(message = "User password can not be empty or null")
    private String password;

    private String photo;
    private boolean actif;

    @Column(name = "date_creation")
    @Temporal(TemporalType.DATE)
    private Date dateCreation;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles;
}
