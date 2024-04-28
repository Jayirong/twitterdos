package com.twitter.twitterdos.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.twitter.twitterdos.model.Comentario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioRepositoryTest {
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Test
    public void guardarComentarioTest(){
        Comentario comentario = new Comentario();
        comentario.setAutor("Tarou");
        comentario.setPublicacionId(1L);
        comentario.setTexto("Lorem Ipsum");

        Comentario resultado = comentarioRepository.save(comentario);

        assertNotNull(resultado.getId());
        assertEquals("Tarou", resultado.getAutor());
        assertEquals(1L, resultado.getPublicacionId());
        assertEquals("Lorem Ipsum", resultado.getTexto());
    }
}
