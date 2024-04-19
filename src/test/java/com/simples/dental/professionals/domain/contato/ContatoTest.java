package com.simples.dental.professionals.domain.contato;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContatoTest {

    private static String EXPECTED_NOME = "fixo";

    private static String EXPECTED_CONTATO = "14-999999999";

    private static String EXPECTED_PROFISSIONAL_ID = "578a1fc9-e6ec-4190-ab36-05c87eeecaf7";

    @Test
    public void givenAValidParams_whenCallNewContato_thenReturnsANewOne() {
        final var contato = Contato.newContato(EXPECTED_NOME, EXPECTED_CONTATO, EXPECTED_PROFISSIONAL_ID);

        assertNotNull(contato);
        assertNotNull(contato.getId());
        assertEquals(EXPECTED_NOME, contato.getNome());
        assertEquals(EXPECTED_CONTATO, contato.getContato());
        assertEquals(EXPECTED_PROFISSIONAL_ID, contato.getProfissionalId());
        assertNotNull(contato.getCreatedDate());
    }
}
