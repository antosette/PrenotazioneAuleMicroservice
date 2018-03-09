package com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.utils;

public class PrenotazioneNonTrovataException extends Exception{
    public PrenotazioneNonTrovataException(String errorMessage) {
        super(errorMessage); //l'errore verrà passato alla superclasse.
    }
}
