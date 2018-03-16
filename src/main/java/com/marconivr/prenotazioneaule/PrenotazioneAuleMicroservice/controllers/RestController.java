package com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.controllers;


import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.entities.Prenotazione;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.entities.Utente;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.services.LoginService;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.services.PrenotazioneService;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.utils.JsonResponseBody;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.utils.UserNotLoggedException;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Optional;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private static final Logger log = LoggerFactory.getLogger(RestController.class);

    @Autowired
    PrenotazioneService prenotazioneService;

    @Autowired
    LoginService loginService;


    @CrossOrigin
    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity<JsonResponseBody> loginUser(@RequestParam(value ="username") String username, @RequestParam(value="password") String pwd){
        try {
            //la password dovrà essere crittografata. Per semplicità qui viene lasciata in chiaro.
            Optional<Utente> userr = loginService.getUserFromDbAndVerifyPassword(username, pwd);
            if(userr.isPresent()){
                Utente user = userr.get();
                String jwt = loginService.createJwt(user.getId(), user.getUsername(), user.getRuolo(), new Date());
                return ResponseEntity.status(HttpStatus.OK).header("jwt", jwt).body(new JsonResponseBody(HttpStatus.OK.value(), "Login riuscito!"));
            }
        }catch (UserNotLoggedException e1){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Login fallito!Credenziali non valide." + e1.toString()));
        }catch (UnsupportedEncodingException e2){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Errore nella generazione del token."+ e2.toString()));
        }catch (Exception e3) {
            //in fase di test eventuali eccezioni rilevate dovranno essere gestite con un oggetto Exception specializzato.
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Errore generico." + e3.toString()));
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Utente non registrato."));
    }

    @RequestMapping(value = "/prenotazioni", method=POST) //aggiunge una prenotazione
    public ResponseEntity<JsonResponseBody> addOperation(HttpServletRequest request, @Valid Prenotazione prenotazione, BindingResult bindingResult){
        //request -> fetch JWT -> check validity -> Get operations from the user account
        try {
            loginService.verifyJwtAndGetData(request);//user verified
            if(bindingResult.hasErrors()){   //L'oggetto binding result permette di gestire gli errori di validazione dei dati inviati restituendo un opportuno errore in formato JSON
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Dati non validi." + bindingResult.toString()));
            }
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), prenotazioneService.aggiungiPrenotazione(prenotazione)));
        }catch(UnsupportedEncodingException e1){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Unsupported Encoding: " + e1.toString()));
        }catch (UserNotLoggedException e2) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "User not correctly logged: " + e2.toString()));
        }catch(ExpiredJwtException e3){
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new JsonResponseBody(HttpStatus.GATEWAY_TIMEOUT.value(), "Session Expired!: " + e3.toString()));
        }catch (Exception e3) {
            //in fase di test eventuali eccezioni rilevate dovranno essere gestite con un oggetto Exception specializzato.
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Errore generico." + e3.toString()));
        }
    }

    @RequestMapping(value = "/utenti/{utente}/prenotazioni", method=GET)
    public ResponseEntity<JsonResponseBody> fetchAllOperationsPerAccount(HttpServletRequest request, @PathVariable(name = "utente") String idUtente) {
        //inserire la validazione del token qui..
        try {
            loginService.verifyJwtAndGetData(request);//user verified
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), prenotazioneService.recuperaPrenotazioniPerUtente(idUtente)));
        }catch(UnsupportedEncodingException e1){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Unsupported Encoding: " + e1.toString()));
        }catch (UserNotLoggedException e2) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "User not correctly logged: " + e2.toString()));
        }catch(ExpiredJwtException e3){
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new JsonResponseBody(HttpStatus.GATEWAY_TIMEOUT.value(), "Session Expired!: " + e3.toString()));
        }catch (Exception e3) {
            //in fase di test eventuali eccezioni rilevate dovranno essere gestite con un oggetto Exception specializzato.
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Errore generico." + e3.toString()));
        }
   }

   //Handler per gestire l'eccezione MissingServletRequestParameterException
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<JsonResponseBody>  handleMissingParams(MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Errore generico." + ex.toString()));
    }
}
