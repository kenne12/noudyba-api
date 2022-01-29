package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.Rubrique;
import org.kenne.noudybaapi.domain.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
}
