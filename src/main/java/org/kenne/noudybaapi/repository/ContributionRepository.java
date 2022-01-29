package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.Annee;
import org.kenne.noudybaapi.domain.Contribution;
import org.kenne.noudybaapi.domain.Souscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContributionRepository extends JpaRepository<Contribution, Long> {

    @Query("select c from Contribution c where c.membre.idMembre=:idMembre")
    List<Contribution> findContributionByIdMembre(@Param(value = "idMembre") Long idMembre);

    @Query("select c from Contribution c where c.evenement.idEvenement=:idEvenement")
    List<Contribution> findContributionByIdevenement(@Param("idEvenement") Long idEvenement);

}
