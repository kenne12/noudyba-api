package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.Membre;
import org.kenne.noudybaapi.domain.Rubrique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RubriqueRepository extends JpaRepository<Rubrique, Integer> {

    @Query("select max (r.idRubrique) from Rubrique r")
    Integer nextId();
}
