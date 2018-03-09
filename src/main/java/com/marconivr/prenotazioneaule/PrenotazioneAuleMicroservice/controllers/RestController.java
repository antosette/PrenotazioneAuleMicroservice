package com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.controllers;


import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.entities.Prenotazione;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.services.PrenotazioneService;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.utils.JsonResponseBody;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private static final Logger log = LoggerFactory.getLogger(RestController.class);

    @Autowired
    PrenotazioneService prenotazioneService;


    @RequestMapping(value = "/prenotazioni/aggiungi", method=PUT)
    public ResponseEntity<JsonResponseBody> addOperation(HttpServletRequest request, @Valid Prenotazione prenotazione, BindingResult bindingResult){
        //L'oggetto binding result permette di gestire gli errori di validazione dei dati inviati restituendo un opportuno errore in formato JSON
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Dati non validi." + bindingResult.toString()));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), prenotazioneService.aggiungiPrenotazione(prenotazione)));
    }

    @RequestMapping("/prenotazioni/utente/{utente}")
    public ResponseEntity<JsonResponseBody> fetchAllOperationsPerAccount(HttpServletRequest request, @PathVariable(name = "utente") String utente) {
        //inserire la validazione del token qui..
        return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), prenotazioneService.recuperaPrenotazioniPerUtente(utente)));
   }
}
