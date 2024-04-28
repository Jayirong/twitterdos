package com.twitter.twitterdos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.twitterdos.model.Comentario;
import com.twitter.twitterdos.service.ComentarioService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;


//HATEOAS


@RestController
@RequestMapping("/publicaciones/{id}/comentarios")
public class ComentarioController {
    //Hacer Post, Get, Get por id, Put y Delete
    @Autowired
    private ComentarioService comentarioService;

    //C
    @PostMapping
    public EntityModel<Comentario> createPublicacion(@PathVariable Long id, @RequestBody Comentario comentario){
        Comentario createdComentario = comentarioService.createComentario(id, comentario);
        return EntityModel.of(createdComentario,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getComentario(id, createdComentario.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getComentarios(id)).withRel("all-comentarios"));
    }
    
    //R ALL
    @GetMapping
    public CollectionModel<EntityModel<Comentario>> getComentarios(@PathVariable Long id){
        List<Comentario> comentarios = comentarioService.getComentariosByPublicacion(id);
        
        List<EntityModel<Comentario>> comentarioResources = comentarios.stream()
                        .map(comentario -> EntityModel.of(comentario, 
                                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getComentario(id, comentario.getId())).withSelfRel()
                            ))
                        .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getComentarios(id));
        CollectionModel<EntityModel<Comentario>> resources = CollectionModel.of(comentarioResources, linkTo.withRel("comentarios"));
        return resources;
    }
    
    //R ID
    @GetMapping("/{comentarioId}")
    public EntityModel<Comentario> getComentario(@PathVariable Long id, @PathVariable Long comentarioId){
        Optional<Comentario> comentario = comentarioService.getComentarioByPublicacion(id);

        if (comentario.isPresent()){
            return EntityModel.of(comentario.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getComentario(id, comentarioId)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getComentarios(id)).withRel("all-comentarios-publicacion-"+id));
        } else {
            throw new PublicacionNotFoundException("Venta no encontrada, no esssiste una venta con id "+id);
        }

    }

    //U
    @PutMapping("/{comentarioId}")
    public EntityModel<Comentario> updateComentario(@PathVariable Long id, @PathVariable Long comentarioId, @RequestBody Comentario comentario) {
        Comentario updatedComentario = comentarioService.updateComentario(comentarioId, comentario);
        return EntityModel.of(updatedComentario,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getComentario(id, comentarioId)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getComentarios(id)).withRel("all-comentarios-publicacion-"+id));
    }

    //D
    @DeleteMapping("/{comentarioId}")
    public void deleteComentario(@PathVariable Long comentarioId){
        comentarioService.deleteComentario(comentarioId);
    }

}
