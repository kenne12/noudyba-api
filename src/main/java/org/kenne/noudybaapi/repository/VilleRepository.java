package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Integer> {

    @Query("select max (v.idVille) from Ville v")
    Integer nextId();
}
