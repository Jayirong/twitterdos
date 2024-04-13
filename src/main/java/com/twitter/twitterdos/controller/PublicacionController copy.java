package com.twitter.twitterdos.controller;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.springframework.web.bind.annotation.RestController;

import com.twitter.twitterdos.model.Comentario;
import com.twitter.twitterdos.model.Publicacion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@RestController
public class PublicacionController {
    //se crea la lista con las publicaciones, haciendo de base de datos provisoria por efectos de esta actividad
    private List<Publicacion> publicaciones = new ArrayList<>();

    @GetMapping("/publicaciones")
    public ResponseEntity<List<Map<String, Object>>> getPublicaciones() {
        List<Map<String, Object>> publicacionesMap = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            Map<String, Object> publicacionMap = crearMapPublicacion(publicacion);
            publicacionesMap.add(publicacionMap);
        }
        return ResponseEntity.ok(publicacionesMap);
    }
    //hasta donde me daté no es muy recomendable usar RawTypes, lo que sería el "ResponseEntity<?>" en este caso, puesto que usa un poco mas de recursos en hacer la ejecución 
    //pero por efectos no solo se podría retornar un "Map<String, Object>", entonces lo pongo raw más que nada porque no me dejó con un determinado tipo
    @GetMapping("/publicaciones/{id}")
    public ResponseEntity<?> getPublicacionById(@PathVariable int id) {
        for (Publicacion publicacion : publicaciones)
            if (publicacion.getIdPublicacion() == id) {
                Map<String, Object> publicacionMap = crearMapPublicacion(publicacion);
                return ResponseEntity.ok(publicacionMap);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aún no hay tantas publicaciones, jiji, vuelve a las más antiguas");
    }
    //el Endpoint que deberia devolver el promedio de las calificaciones
    @GetMapping("publicaciones/promedio/{idPublicacion}")
    public ResponseEntity<Double> getPromedioCalificaciones(@PathVariable int idPublicacion) {
        //buscamos la publicacion por su id
        //notas pa mi importantes al final
        Publicacion publicacion = publicaciones.stream().filter(p -> p.getIdPublicacion() == idPublicacion).findFirst().orElse(null);
        //verificamos la existencia de la publicacion  
        if (publicacion != null) {
            //una vez comprobamos que efectivamente existe obtenemos la lista de calificaciones y calculamos el promedio, en caso de por abc motivo no poder calvular un promedio retorna 0.0
            List<Integer> calificaciones = publicacion.getCalificaciones();
            Double promedio = calificaciones.stream().mapToInt(Integer::intValue).average().orElse(0.0);
            return ResponseEntity.ok(promedio);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    //ordenar publicaciones por calificacion descendente
    @GetMapping("publicaciones/top")
    public ResponseEntity<List<Map<String, Object>>> getPublicacionesOrdenadasPorCalificacion() {
        //creamos una copia de la lista original y ordenamos las copias de las publicaciones por calificacion de forma descendente
        List<Publicacion> copiaPublicaciones =  new ArrayList<>(publicaciones);
        copiaPublicaciones.sort((p1, p2) ->{
            double promedio1 = p1.getCalificaciones().stream().mapToInt(Integer::intValue).average().orElse(0.0);
            double promedio2 = p2.getCalificaciones().stream().mapToInt(Integer::intValue).average().orElse(0.0);
            return Double.compare(promedio2, promedio1);
        });

        List<Map<String, Object>> publicacionesMap = new ArrayList<>();
        for (Publicacion publicacion : copiaPublicaciones){
            Map<String, Object> publicacionMap = crearMapPublicacion(publicacion);
            publicacionesMap.add(publicacionMap);
        }
        return ResponseEntity.ok(publicacionesMap);
    }
        
}
