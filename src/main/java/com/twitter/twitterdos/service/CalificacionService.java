package com.twitter.twitterdos.service;

import java.util.List;

import com.twitter.twitterdos.model.Calificacion;

public interface CalificacionService {
    //La idea es hacer un CRUD aca
    //Create
    Calificacion createCalificacion(Long idPublicacion, Calificacion calificacion);
    //Read
    List<Calificacion> getCalificacionesByPublicacion(Long idPublicacion);
    //Update
    Calificacion updateCalificacion(Long id, Calificacion calificacion);
    //Delete
    void deleteCalificacion(Long id);
}
