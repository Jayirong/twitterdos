package com.twitter.twitterdos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "calificacion")
public class Calificacion {
    //se definen los atributos del objeto "comentario"
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "nota")
    private float nota;

    @Column(name = "publicacionId")
    private Long publicacionId;

    //Getters
    public Long getId(){
        return id;
    }

    public float getNota(){
        return nota;
    }

    public Long getPublicacionId(){
        return publicacionId;
    }

    //Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setNota(float nota){
        this.nota = nota;
    }

    public void setPublicacionId(Long publicacionId){
        this.publicacionId = publicacionId;
    }

}
