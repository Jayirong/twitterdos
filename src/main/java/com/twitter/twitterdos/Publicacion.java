package com.twitter.twitterdos;

import java.util.List;

//se define el objeto publicacion con sus atributos
public class Publicacion {
    private int id;
    private String autor;
    private String texto;
    //cabe resaltar que las calificaciones van aparte del comentario puesto que no es necesario comentar para calificar
    private List <Comentario> comentarios;
    private List<Integer> calificaciones;

//Constructor
    public Publicacion(int id, String autor, String texto, List <Comentario> comentarios, List<Integer> calificaciones){
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

    public List <Comentario> getComentarios(){
        return comentarios;
    }

    public List<Integer> getCalificaciones(){
        return calificaciones;
    }

}



