package com.twitter.twitterdos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.twitter.twitterdos.model.Publicacion;
import com.twitter.twitterdos.repository.PublicacionRepository;

@ExtendWith(MockitoExtension.class)
public class PublicacionServiceTest {
    @InjectMocks
    private PublicacionServiceImpl publicacionService;

    @Mock
    private PublicacionRepository publicacionRepositoryMock;

    @Test
    public void guardarPublicacionTest(){
        //Arrange :0
        Publicacion publicacion = new Publicacion(); 
        publicacion.setAutor("Hanako");
        publicacion.setTexto("cafe instantaneo colombiano");

        when(publicacionRepositoryMock.save(any())).thenReturn(publicacion);

        //Act :1
        Publicacion resultado = publicacionService.createPublicacion(publicacion);

        //Assert :2
        assertEquals("Hanako", resultado.getAutor());
        assertEquals("cafe instantaneo colombiano", resultado.getTexto());

    }
    
}
