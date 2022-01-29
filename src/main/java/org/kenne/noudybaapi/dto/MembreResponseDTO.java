package org.kenne.noudybaapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.kenne.noudybaapi.domain.Ville;

import javax.persistence.*;

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

    private Ville ville;

    @Override
    public String toString() {
        return "MembreResponseDTO{" +
                "idMembre=" + idMembre +
                ", code='" + code + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", contact='" + contact + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
