package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    @Query("select max (u.idUtilisateur) from Utilisateur  u")
    Integer nextId();

    Optional<Utilisateur> findByUsername(String username);
}
