package com.twitter.twitterdos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PublicacionNotFoundException extends RuntimeException{

    public PublicacionNotFoundException(String mensaje){
        super(mensaje);
    }
    
}