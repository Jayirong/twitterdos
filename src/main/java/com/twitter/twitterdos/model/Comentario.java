package com.twitter.twitterdos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "comentario")
public class Comentario {
    //se definen los atributos del objeto "comentario"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "texto")
    private String texto;
    
    @Column(name = "autor")
    private String autor;

    @Column(name = "publicacionId")
    private Long publicacionId;

    
    //getters
    public Long getId(){
        return id;
    }

    public String getTexto(){
        return texto;
    }

    public String getAutor(){
        return autor;
    }

    public Long getPublicacionId(){
        return publicacionId;
    }

    //Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setTexto(String texto){
        this.texto = texto;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    public void setPublicacionId(Long publicacionId){
        this.publicacionId = publicacionId;
    }

}
