package com.twitter.twitterdos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.twitterdos.model.Comentario;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
    List<Comentario> findByIdPublicacion(Long idPublicacion);
    
}
