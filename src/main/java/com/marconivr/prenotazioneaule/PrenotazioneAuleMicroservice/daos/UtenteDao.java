package com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.daos;

import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

//<Utente, String>: tipo entità (Utente) e tipo identificativo (String)
public interface UtenteDao extends JpaRepository<Utente, String> {

    Optional<Utente> findById(String id); //implementazione della ricerca tramite Id utilizzando le named query di Spring.
    Utente save(Utente u); //inserimento nuovo utente
    void delete(Utente u); //cancellazione utente
}
