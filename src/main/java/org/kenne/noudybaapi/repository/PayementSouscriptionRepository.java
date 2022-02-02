package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.PayementSouscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayementSouscriptionRepository extends JpaRepository<PayementSouscription, Long> {

    @Query("select p from PayementSouscription p where p.souscription.membre.idMembre=:idMembre")
    List<PayementSouscription> findAllByIdmebre(@Param(value = "idMembre") Long idMembre);

    @Query("select p from PayementSouscription p where p.souscription.idSouscription=:idSouscription")
    List<PayementSouscription> findAllByIdsouscription(@Param(value = "idSouscription") Long idSouscription);

    @Query("select p from PayementSouscription p where p.souscription.evenement.annee.idAnnee=:id_annee order by p.datePayement desc , p.idPayementSouscription desc")
    List<PayementSouscription> findAllByIdannee(@Param("id_annee") Integer idAnnee);
}
