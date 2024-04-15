package com.twitter.twitterdos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.twitterdos.model.Calificacion;

public interface CalificacionRepository extends JpaRepository<Calificacion, Long>{
    List<Calificacion> findByPublicacionId(Long PublicacionId);
    
}
