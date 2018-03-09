package com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice;

import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.daos.PrenotazioneDao;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.daos.UtenteDao;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.entities.Prenotazione;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.entities.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;


@SpringBootApplication
public class PrenotazioneAuleMicroserviceApplication implements CommandLineRunner {
	@Autowired
	UtenteDao utenteDao;

	@Autowired
	PrenotazioneDao prenotazioneDao;

	public static void main(String[] args) {
		SpringApplication.run(PrenotazioneAuleMicroserviceApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		//Inserimento utenti
		utenteDao.save(new Utente("4300","Docente1","Doc_01", "Docente"));
		utenteDao.save(new Utente("4301","Docente2","Doc_02", "Docente"));
		utenteDao.save(new Utente("4302","Segreteria","Pa$$word!", "Segreteria"));
		//Inserimento prenotazioni
		prenotazioneDao.save(new Prenotazione(0,new Date(),"4300","1200",new Date(),2 ));
	}
}
