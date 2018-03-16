/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.services;

import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.entities.Utente;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.utils.UserNotLoggedException;


import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author Antonio
 */
public interface LoginService {
    Optional<Utente> getUserFromDbAndVerifyPassword(String username, String password) throws UserNotLoggedException;

    String createJwt(String subject, String name, String permission, Date date) throws UnsupportedEncodingException;

    Map<String, Object> verifyJwtAndGetData(HttpServletRequest request) throws UserNotLoggedException, UnsupportedEncodingException;

}
