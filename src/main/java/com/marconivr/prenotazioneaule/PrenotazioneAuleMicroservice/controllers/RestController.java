package com.marconivr.prenotazioneaule.PrenotazioneAuleMicroservice.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    private static final Logger log = LoggerFactory.getLogger(RestController.class);
    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello everyone!";
    }

}
