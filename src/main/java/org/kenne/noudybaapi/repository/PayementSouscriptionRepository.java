package org.kenne.noudybaapi.repository;

import org.kenne.noudybaapi.domain.PayementSouscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PayementSouscriptionRepository extends JpaRepository<PayementSouscription, Long> {

    @Query("select p from PayementSouscription p where p.souscription.membre.idMembre=:idMembre")
    List<PayementSouscription> findPayementSouscriptionByIdmebre(@Param(value = "idMembre") Long idMembre);

    @Query("select p from PayementSouscription p where p.souscription.idSouscription=:idSouscription")
    List<PayementSouscription> findPayementSouscriptionByIdsouscription(@Param(value = "idSouscription") Long idSouscription);
}
