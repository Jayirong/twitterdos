package com.twitter.twitterdos.model;

public class Comentario {
    //se definen los atributos del objeto "coemntario"
    private int id;
    private String texto;
    private String autor;

    //constructor
    public Comentario(int id, String texto, String autor){
        this.id = id;
        this.texto = texto;
        this.autor = autor;
    }

    //getters
    public int getIdComentario(){
        return id;
    }

    public String getTextoComentario(){
        return texto;
    }

    public String getAutorComentario(){
        return autor;
    }



}
