package com.twitter.twitterdos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.twitter.twitterdos.model.Publicacion;
import com.twitter.twitterdos.service.PublicacionService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {
    @Autowired
    private PublicacionService publicacionService;

    @GetMapping
    public List<Publicacion> getPublicacion(){
        return publicacionService.getPublicaciones();
    }
    
    @GetMapping("/{id}")
    public Optional<Publicacion> getPublicacionById(@PathVariable Long id) {
        return publicacionService.getPublicacionById(id);
    }
    
    @PostMapping
    public Publicacion createPublicacion(@RequestBody Publicacion publicacion) {
        return publicacionService.createPublicacion(publicacion);
    }
    
    @PutMapping("/{id}")
    public Publicacion updatePublicacion(@PathVariable Long id, @RequestBody Publicacion publicacion) {
        return publicacionService.updatePublicacion(id, publicacion);
    }

    @DeleteMapping("/{id}")
    public void deletePublicacion(@PathVariable Long id){
        publicacionService.deletePublicacion(id);
    }


}
