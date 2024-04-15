package com.twitter.twitterdos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.twitterdos.model.Calificacion;
import com.twitter.twitterdos.service.CalificacionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api")
public class GenericController {
    @Autowired
    private CalificacionService calificacionService;

    private double calcularPromedio(List<Calificacion> calificaciones){
        double sumatoria = calificaciones.stream()
                            .mapToDouble(Calificacion::getNota)
                            .sum();
        return sumatoria / calificaciones.size();
    }

    //promedio de calificaciones de una publicacion
    @GetMapping("/publicaciones/{id}/promedio-calificaciones")
    public ResponseEntity<Double> getPromedioCalificaciones(@PathVariable Long id) {
        List<Calificacion> calificaciones = calificacionService.getCalificacionesByPublicacion(id);
        
        if (calificaciones.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        double promedio = calcularPromedio(calificaciones);
        return ResponseEntity.ok(promedio);
    }
    
    
}
