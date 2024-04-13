package com.twitter.twitterdos.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "id_pubicacion")
    private Publicacion publicacion;

    //Getters
    public Long getId(){
        return id;
    }

    public float getNota(){
        return nota;
    }

    public Publicacion getPublicacion(){
        return publicacion;
    }

    //Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setNota(float nota){
        this.nota = nota;
    }

    public void setPublicacion(Publicacion publicacion){
        this.publicacion = publicacion;
    }

}
