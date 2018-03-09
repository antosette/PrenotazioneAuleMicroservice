package com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.services;


import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.entities.Prenotazione;


import java.util.List;

public interface PrenotazioneService {

    List<Prenotazione> recuperaPrenotazioniPerUtente(String fkutente);

    Prenotazione aggiungiPrenotazione(Prenotazione prenotazione);
}
