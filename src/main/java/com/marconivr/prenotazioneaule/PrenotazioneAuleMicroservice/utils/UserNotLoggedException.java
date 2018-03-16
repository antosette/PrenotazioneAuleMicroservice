/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.utils;

/**
 *
 * @author Antonio
 */
public class UserNotLoggedException extends Exception{
   public UserNotLoggedException (String errorMessage) {
       super(errorMessage); //l'errore verrà passato alla superclasse.
   } 
}
