package com.twitter.twitterdos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.twitterdos.model.Calificacion;
import com.twitter.twitterdos.repository.CalificacionRepository;

@Service
public class CalificacionServiceImpl implements CalificacionService{
    @Autowired
    private CalificacionRepository calificacionRepository;

    @Override
    public Calificacion createCalificacion(Long idPublicacion, Calificacion calificacion){
        return calificacionRepository.save(calificacion);
    }

    @Override
    public List<Calificacion> getCalificacionesByPublicacion(Long idPublicacion){
        return calificacionRepository.findByPublicacionId(idPublicacion);
    }

    @Override
    public Calificacion updateCalificacion(Long id, Calificacion comentario){
        if (calificacionRepository.existsById(id)) {
            comentario.setId(id);
            return calificacionRepository.save(comentario);
        }
        else{
            return null;
        }
    }

    @Override
    public void deleteCalificacion(Long id){
        calificacionRepository.deleteById(id);
    }
}
