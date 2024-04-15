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
    @OneToMany(mappedBy = "publicacionId")
    private List <Comentario> comentarios;

    @OneToMany(mappedBy = "publicacionId")
    private List<Calificacion> calificaciones;

    //por efectos, nos fumamos el constructor
    
    //Getter
    public Long getId(){
        return id;
    }

    public String getAutor(){
        return autor;
    }

    public String getTexto(){
        return texto;
    }

    public List <Comentario> getComentarios(){
        return comentarios;
    }

    public List<Calificacion> getCalificaciones(){
        return calificaciones;
    }

    //Setters

    public void setId(Long id){
        this.id = id;
    }

    public void setAutor(String autor){
        this.autor = autor;
    }

    public void setTexto(String texto){
        this.texto = texto;
    }

    public void setComentarios(List<Comentario> comentarios){
        this.comentarios = comentarios;
    }

    public void setCalificaciones(List<Calificacion> calificaciones){
        this.calificaciones = calificaciones;
    }

}



