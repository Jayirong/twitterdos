package com.twitter.twitterdos.service;

import java.util.List;

import com.twitter.twitterdos.model.Calificacion;

public interface CalificacionService {
    //La idea es hacer un CRUD aca
    //Create
    Calificacion createCalificacion(Long publicacionId, Calificacion calificacion);
    //Read
    List<Calificacion> getCalificacionesByPublicacion(Long publicacionId);
    //Update
    Calificacion updateCalificacion(Long id, Calificacion calificacion);
    //Delete
    void deleteCalificacion(Long id);
}
