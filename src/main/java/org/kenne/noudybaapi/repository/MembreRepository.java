package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.Membre;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembreRepository extends JpaRepository<Membre, Long> {

    Optional<Membre> findByCode(String code);

    @Query("select max (m.idMembre) from Membre m")
    Long findMaxIdmembre();

    List<Membre> findAllByState(boolean state, Sort sort);

}
