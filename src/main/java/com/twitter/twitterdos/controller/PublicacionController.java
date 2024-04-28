package com.twitter.twitterdos.controller;

import org.springframework.web.bind.annotation.RestController;

import com.twitter.twitterdos.model.Publicacion;
import com.twitter.twitterdos.service.PublicacionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




//HATEOAS DE AKI PABAJO

@RestController
@RequestMapping("/publicaciones")
public class PublicacionController {
    @Autowired
    private PublicacionService publicacionService;

    //R ALL
    @GetMapping
    public CollectionModel<EntityModel<Publicacion>> getPublicaciones(){
        List<Publicacion> publicaciones = publicacionService.getPublicaciones();
        
        List<EntityModel<Publicacion>> publicacionResources = publicaciones.stream()
                        .map(publicacion -> EntityModel.of(publicacion, 
                                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPublicacionById(publicacion.getId())).withSelfRel()
                            ))
                        .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPublicaciones());
        CollectionModel<EntityModel<Publicacion>> resources = CollectionModel.of(publicacionResources, linkTo.withRel("ventas"));
        return resources;
    }
    
    

    //R ID
    @GetMapping("/{id}")
    public EntityModel<Publicacion> getPublicacionById(@PathVariable Long id){
        Optional<Publicacion> publicacion = publicacionService.getPublicacionById(id);

        if (publicacion.isPresent()){
            return EntityModel.of(publicacion.get(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPublicacionById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPublicaciones()).withRel("all-publicaciones"));
        } else {
            throw new PublicacionNotFoundException("Venta no encontrada, no esssiste una venta con id "+id);
        }

    }
    
    //C
    @PostMapping
    public EntityModel<Publicacion> createPublicacion(@RequestBody Publicacion publicacion){
        Publicacion createdPublicacion = publicacionService.createPublicacion(publicacion);
        return EntityModel.of(createdPublicacion,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPublicacionById(createdPublicacion.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPublicaciones()).withRel("all-publicaciones"));
    }
    
    //U
    @PutMapping("/{id}")
    public EntityModel<Publicacion> updatePublicacion(@PathVariable Long id, @RequestBody Publicacion publicacion) {
        Publicacion updatedPublicacion = publicacionService.updatePublicacion(id, publicacion);
        return EntityModel.of(updatedPublicacion,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPublicacionById(updatedPublicacion.getId())).withSelfRel(),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPublicaciones()).withRel("all-publicaciones"));
    }

    //D
    @DeleteMapping("/{id}")
    public void deletePublicacion(@PathVariable Long id){
        publicacionService.deletePublicacion(id);
    }


}
