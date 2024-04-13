package com.twitter.twitterdos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.twitter.twitterdos.model.Publicacion;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long>{

    
}
