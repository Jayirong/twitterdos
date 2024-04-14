package com.twitter.twitterdos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.twitterdos.model.Comentario;
import com.twitter.twitterdos.service.ComentarioService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/publicaciones/{id}/comentarios")
public class ComentarioController {
    //Hacer Post, Get, Get por id, Put y Delete
    @Autowired
    private ComentarioService comentarioService;

    @PostMapping
    public Comentario createcComentario(@PathVariable Long id, @RequestBody Comentario comentario) {        
        return comentarioService.createComentario(id, comentario);
    }
    
    @GetMapping
    public List<Comentario> getComentarios(@PathVariable Long id) {
        return comentarioService.getComentariosByPublicacion(id);
    }
    
    @GetMapping("/{comentarioId}")
    public Optional<Comentario> getComentario(@PathVariable Long id, @PathVariable Long comentarioId) {
        return comentarioService.getComentarioByPublicacion(comentarioId);
    }
    
    @PutMapping("/{comentarioId}")
    public Comentario updateComentario(@PathVariable Long comentarioId, @RequestBody Comentario comentario) {        
        return comentarioService.updateComentario(comentarioId, comentario);
    }
    
    @DeleteMapping("/{comentarioId}")
    public void deleteComentario(@PathVariable Long comentarioId){
        comentarioService.deleteComentario(comentarioId);
    }

}
