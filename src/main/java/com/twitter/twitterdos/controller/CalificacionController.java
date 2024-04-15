package com.twitter.twitterdos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.twitterdos.model.Calificacion;
import com.twitter.twitterdos.service.CalificacionService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/publicaciones/{id}/calificaciones")
public class CalificacionController {
    @Autowired
    private CalificacionService calificacionService;
    //Hacer Post, Get, Get por id, Put y Delete

    @PostMapping
    public Calificacion createCalificacion(@PathVariable Long id, @RequestBody Calificacion calificacion) {
        return calificacionService.createCalificacion(id, calificacion);
    }
    
    @GetMapping
    public List<Calificacion> getCalificaciones(@PathVariable Long id) {
        return calificacionService.getCalificacionesByPublicacion(id);
    }

    @PutMapping("/{calificacionId}")
    public Calificacion updateCalificacion(@PathVariable Long calificacionId, @RequestBody Calificacion calificacion) {        
        return calificacionService.updateCalificacion(calificacionId, calificacion);
    }
    
    @DeleteMapping("/{calificacionId}")
    public void deleteCalificacion(@PathVariable Long calificacionId){
        calificacionService.deleteCalificacion(calificacionId);
    }
    
}
