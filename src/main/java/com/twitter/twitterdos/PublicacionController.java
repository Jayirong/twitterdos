package com.twitter.twitterdos;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



//Especificamos que dentro del framework Spring Boot esto es un controlador
@RestController
public class PublicacionController {
    //se crea la lista con las publicaciones, haciendo de base de datos provisoria por efectos de esta actividad
    private List<Publicacion> publicaciones = new ArrayList<>();


    public PublicacionController(){
        //inicializamos la lista con datos de prueba
        publicaciones.add(new Publicacion(1, "Pepito", "Los gatos y los perros estan sobrevalorados", 
                                            Arrays.asList(new Comentario(1, "tai meadini", "Car lover"),
                                                        new Comentario(2, "Ya, igual si, si los gatos son entero pavos", "Lolein")),
                                            Arrays.asList(1, 58, 82, 91)));

        publicaciones.add(new Publicacion(2, "Jorel", "Gente, estoy lol", 
                                            Arrays.asList(new Comentario(1, "aaaaay yo igual <3", "Jorel Fanzzz"),
                                                        new Comentario(2, "tekero muchooooo", "Artemizzza")),
                                            Arrays.asList(100, 99, 99, 89, 95, 99)));

        publicaciones.add(new Publicacion(3, "Guaton Ramsay", "Pipol, manden sus truquilocos de cocina", 
                                            Arrays.asList(new Comentario(1, "Cuando se te esté quemando la comida apaga el fuego", "Skyrim lover XOXO"),
                                                        new Comentario(2, "Trata de mantener con frecuencia tu basurero vacío", "Raccoons"),
                                                        new Comentario(3, "Usa un sifón para hacer unas espumitas bien cabronas, no solo crema chantilly", "ElJugoso123"),
                                                        new Comentario(4, "Los enlatados son god, que no falten en tu despensa", "Alan Brito Delgado")),
                                            Arrays.asList(67, 45, 73, 27, 89, 92)));

        publicaciones.add(new Publicacion(4, "Rosa espinoza", "Debería teñirme el pelo?", 
                                            Arrays.asList(new Comentario(1, "No", "Pelao Schuster")),
                                            Arrays.asList(2)));
    }

    //dado a que el JSON me salía desordenado y después de probar distintos métodos sin poder ordenarlo
    //finalmente encontré esta forma, un LinkedHashMap, el cual externalicé para usarlo en posteriores métodos, básicamente crea un mapa ordenado a mi gusto
    private Map<String, Object> crearMapPublicacion(Publicacion publicacion){
        Map<String, Object> publicacionMap = new LinkedHashMap<>();
            publicacionMap.put("id", publicacion.getIdPublicacion());
            publicacionMap.put("autor", publicacion.getAutorPublicacion());
            publicacionMap.put("texto", publicacion.getTextoPublicacion());
            publicacionMap.put("comentarios", publicacion.getComentarios());
            publicacionMap.put("calificaciones", publicacion.getCalificaciones());
            return publicacionMap;
    }

    @GetMapping("/publicaciones")
    public ResponseEntity<List<Map<String, Object>>> getPublicaciones() {
        List<Map<String, Object>> publicacionesMap = new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
            Map<String, Object> publicacionMap = crearMapPublicacion(publicacion);
            publicacionesMap.add(publicacionMap);
        }
        return ResponseEntity.ok(publicacionesMap);
    }

    @GetMapping("/publicaciones/{id}")
    public ResponseEntity<Map<String, Object>> getPublicacionById(@PathVariable int id) {
        for (Publicacion publicacion : publicaciones)
            if (publicacion.getIdPublicacion() == id) {
                Map<String, Object> publicacionMap = crearMapPublicacion(publicacion);
                return ResponseEntity.ok(publicacionMap);
            }
            return null;
    }
    
    
}
