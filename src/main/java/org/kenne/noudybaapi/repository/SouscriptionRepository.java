package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.Contribution;
import org.kenne.noudybaapi.domain.Poste;
import org.kenne.noudybaapi.domain.Souscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface SouscriptionRepository extends JpaRepository<Souscription, Long> {

    @Query("select max (s.idSouscription) from Souscription s")
    Long nexId();

    @Query("select s from Souscription s where s.membre.idMembre=:idMembre")
    List<Souscription> findContributionByIdMembre(@Param(value = "idMembre") Long idMembre);

    @Query("select s from Souscription s where s.evenement.idEvenement=:idEvenement")
    List<Souscription> findContributionByIdevenement(@Param(value = "idEvenement") Long idEvenement);
}
