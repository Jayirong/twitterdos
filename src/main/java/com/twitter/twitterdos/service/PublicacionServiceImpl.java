package com.twitter.twitterdos.service;

import com.twitter.twitterdos.model.Publicacion;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.twitterdos.repository.PublicacionRepository;

@Service
public class PublicacionServiceImpl implements PublicacionService{
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Override
    public Publicacion createPublicacion(Publicacion publicacion){
        return publicacionRepository.save(publicacion);
    }

    @Override
    public List<Publicacion> getPublicaciones(){
        return publicacionRepository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public Optional<Publicacion> getPublicacionById(Long id){
        return publicacionRepository.findById(id);
    }

    @Override
    public Publicacion updatePublicacion(Long id, Publicacion publicacion){
        if(publicacionRepository.existsById(id)){
            publicacion.setId(id);
            return publicacionRepository.save(publicacion);
        }
        else{
            return null;
        }
    }

    @Override
    public void deletePublicacion(Long id){
        publicacionRepository.deleteById(id);
    }
    


}
