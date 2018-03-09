package com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.services;

import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.daos.PrenotazioneDao;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.entities.Prenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service @Transactional
public class PrenotazioneServiceImpl implements PrenotazioneService {

    @Autowired
    PrenotazioneDao prenotazioneDao;

    @Override
    public Prenotazione aggiungiPrenotazione(Prenotazione prenotazione) {
        return prenotazioneDao.save(prenotazione);
    }

    @Override
    public List<Prenotazione> recuperaPrenotazioniPerUtente(String utente) {
        return prenotazioneDao.findByfkUtente(utente);
    }
}
