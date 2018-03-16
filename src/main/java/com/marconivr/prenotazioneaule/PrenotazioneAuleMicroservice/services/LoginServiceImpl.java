package com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.services;


import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.daos.UtenteDao;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.entities.Utente;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.utils.JwtUtils;
import com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.utils.UserNotLoggedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{

    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    UtenteDao utenteDao;


    @Override
    public Optional<Utente> getUserFromDbAndVerifyPassword(String username, String password) throws UserNotLoggedException {

        Optional<Utente> userr = utenteDao.findByusername(username);
        if(userr.isPresent()){
            Utente user = userr.get();
            if(user.getPassword().equals(password)){
                log.info("Username and Password verified");
            }else{
                log.info("Username verified. Password not");
                throw new UserNotLoggedException("User not correctly logged in");
            }
        }
        return userr;
    }


    /**
     *
     * @param subject
     * @param name
     * @param permission
     * @param datenow
     * @return
     * @throws UnsupportedEncodingException se i dati passati non sono corretti.
     */
    @Override
    public String createJwt(String subject, String name, String permission, Date datenow) throws UnsupportedEncodingException{
        Date expDate = datenow;
        expDate.setTime(datenow.getTime() + (300*1000));
        log.info("JWT Creation. Expiration time: " + expDate.getTime());
        String token = JwtUtils.generateJwt(subject, expDate, name, permission);
        return token;
    }


    /**
     *
     * @param request
     * @return
     * @throws UserNotLoggedException
     * @throws UnsupportedEncodingException se il token non è valido la sua codifica non sarà corretta.
     */
    @Override
    public Map<String, Object> verifyJwtAndGetData(HttpServletRequest request) throws UserNotLoggedException, UnsupportedEncodingException{
        String jwt = JwtUtils.getJwtFromHttpRequest(request);
        if(jwt == null){
            throw new UserNotLoggedException("Authentication token not found in the request");
        }
        Map<String, Object> userData = JwtUtils.jwt2Map(jwt);
        return userData;
    }


}
