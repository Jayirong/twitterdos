package com.twitter.twitterdos.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// Al marcar una clase con @Entity, estás diciendo que deseas que la clase sea tratada como una entidad en el contexto de 
// persistencia de JPA y que sus instancias se puedan almacenar, actualizar y recuperar de la base de datos de manera transparente. --Fuentes: Chat Gepetto

//se define el objeto publicacion con sus atributos

@Entity
@Table(name = "publicacion")
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "autor")
    private String autor;
    @Column(name = "texto")
    private String texto;
    //cabe resaltar que las calificaciones van aparte del comentario puesto que no es necesario comentar para calificar
    //el @OneToMany(mappedBy = "Tupoto") vendria siendo lo que es una relacion uno es a muchos, una pub tiene varios comentarios, esto se refleja en la tabla de DB como una FK
    @OneToMany(mappedBy = "publicacion")
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



