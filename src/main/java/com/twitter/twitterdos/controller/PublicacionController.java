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

//nota pa mi-- "Stream" convierte una coleccion a un flujo de elementos de tipo Stream lo que en resumen sirve para hacer mas tipos de operaciones a los elementos como filtrado, mapeo o reduccion de elementos
//nota pa mi-- dentro de ".filter(...)" se indica un "predicado", expresion booleana que indica si un elemento debe ser incluido en el flujo o no, que es un flitro xD
//              lo que esta contenido dentro de los parametros es una expresion "lambda", dicha verifica si el id de la publicacion es el mismo que el entregado en el argumento "{idPublicacion}"
//              esto crea un flujo donde solo se contienen las publicaciones cuyo id sea coincidente con el argumento, dicho flujo luego se puede procesar. Se usa .findFirst
//              porque sabemos que solo deberia haver un puro resultado y el .orElse se usa con .finFirst para proporcionar un valor determinado para cuando no se encuentre lo que se busca.
//nota pa mi-- una expresion lambda vendria siendo una funcion anonima, una funcion sin nombre especifico que puede ser pasada como argumento a metodos o almacenada en variables
//              esta consta de 3 partes, 1- parametros, pueden ser 0 o mas si son mas de uno se colocan en parentesis y se separan por comas (x, y). 2- operador flecha '->', es para separar los parametros del cuerpo
//              se puede abstraer como "se convierte en" o "va a". 3- cuerpo, puede ser una unica expresion, como "x -> x * x" o un bloque de codigo encerrado en llaves "(int x, int y) -> {int sum = x + y; return sum;}"
//nota pa mi-- el metodo .mapToInt() perteneciente a stream() convierte el flujo de datos de stream en un IntStream, lo que contiene los valores enteros del stream para realizar operaciones en los datos primitivos int, esto lo hace con un mapeo
//nota pa mi-- la diferencia sustancial entre 'int' e 'Integer' es que 'int' es basicamente un dato primitivo e Integer es una clase que envuelve el valor int, s sigo sin entender por que se usan unos en vez de otros.
//nota pa mi-- Dentro del '.mapToInt()' encontramos un '::',  esto sirve para hacer referencia a un metodo en este caso concreto se usa como referencia del metodo 'intValue' de la clase 'Integer'. 
//              segun chat Gepetto: "es equivalente a escribir una expresión lambda (Integer i) -> i.intValue(), donde i.intValue() es el método que convierte un objeto Integer en su valor int subyacente."
//nota pa mi-- en la linea 108 tenemos un '.sort()' esto ordena de acuerdo a un criterio especifico, en nuestro caso usamos la expresion lambda, 'p1' y 'p2' representan 2 elementos de la lista que se estan comparando
//              en el cuerpo se calcula el promedio  y se usa 'Double.compare()' para comparar los promedios, si p2 es mayor a p1 entonces 'compare' devolvera un valor positivo, lo que le indica que p2 va antes que p1 en la lista