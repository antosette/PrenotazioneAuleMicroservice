package com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.daos;

import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.entities.Prenotazione;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PrenotazioneDao extends JpaRepository<Prenotazione, String> {
    Optional<Prenotazione> findByid(String id); //ricerca prenotazione

    @Query(value = "SELECT * FROM prenotazioni WHERE FK_UTENTE=:utente", nativeQuery = true)
    List<Prenotazione> findByfkUtente(@Param("utente") String utente); //ricerca prenotazioni di un utente

    List<Prenotazione> findByfkAula(String fkAula); //ricerca

    Prenotazione save(Prenotazione prenotazione); //inserimento nuova prenotazione

    void delete(Prenotazione prenotazione); //cancellazione prenotazione
}
