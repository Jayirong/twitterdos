package com.twitter.twitterdos.service;

import com.twitter.twitterdos.model.Publicacion;
import java.util.Optional;
import java.util.List;

public interface PublicacionService {
    //La idea es hacer un CRUD aca
    //Create
    Publicacion createPublicacion(Publicacion publicacion);
    //Read
    List<Publicacion> getPublicaciones();
    Optional<Publicacion> getPublicacionById(Long id);
    //Update
    Publicacion updatePublicacion(Long id, Publicacion publicacion);
    //Delete
    void deletePublicacion(Long id);
    
}
