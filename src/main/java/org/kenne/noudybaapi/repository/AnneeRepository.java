package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.Annee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnneeRepository extends JpaRepository<Annee, Integer> {

    @Query("select max (a.idAnnee) from Annee a")
    Integer nextId();
}
