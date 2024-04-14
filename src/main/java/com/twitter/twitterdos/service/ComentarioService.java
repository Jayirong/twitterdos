package com.twitter.twitterdos.service;

import com.twitter.twitterdos.model.Comentario;
import java.util.List;

public interface ComentarioService {
    //La idea es hacer un CRUD aca
    //Create
    Comentario createComentario(Long idPublicacion, Comentario comentario);
    //Read
    List<Comentario> getComentariosByPublicacion(Long idPublicacion);
    //Update
    Comentario updateComentario(Long id, Comentario comentario);
    //Delete
    void deleteComentario(Long id);
    
}
