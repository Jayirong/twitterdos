package com.twitter.twitterdos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.twitterdos.model.Comentario;
import com.twitter.twitterdos.repository.ComentarioRepository;

@Service
public class ComentarioServiceImpl implements ComentarioService{
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Override
    public Comentario createComentario(Long idPublicacion, Comentario comentario){
        return comentarioRepository.save(comentario);
    }

    @SuppressWarnings("null")
    @Override
    public List<Comentario> getComentariosByPublicacion(Long idPublicacion){
        return comentarioRepository.findByIdPublicacion(idPublicacion);
    }

    @Override
    public Comentario updateComentario(Long id, Comentario comentario){
        if (comentarioRepository.existsById(id)) {
            comentario.setId(id);
            return comentarioRepository.save(comentario);
        }
        else{
            return null;
        }
    }

    @Override
    public void deleteComentario(Long id){
        comentarioRepository.deleteById(id);
    }
}
