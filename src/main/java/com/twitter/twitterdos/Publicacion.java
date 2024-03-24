package com.twitter.twitterdos;

import java.util.List;
import java.util.Map;

//se define el objeto publicacion con sus atributos

public class Publicacion {
    private int id;
    private String autor;
    private String texto;
    private Map<Integer, Comentario> comentarios;
    private List<Integer> calificaciones;

//Constructor
    public Publicacion(int id, String autor, String texto, Map<Integer, Comentario> comentarios, List<Integer> calificaciones){
        this.id = id;
        this.autor = autor;
        this.texto = texto;
        this.comentarios = comentarios;
        this.calificaciones = calificaciones;
        
    }

    //Getter
    public int getIdPublicacion(){
        return id;
    }

    public String getAutorPublicacion(){
        return autor;
    }

    public String getTextoPublicacion(){
        return texto;
    }

    public Map<Integer, Comentario> getComentarios(){
        return comentarios;
    }

    public List<Integer> getCalificaciones(){
        return calificaciones;
    }

}



