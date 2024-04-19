package com.simples.dental.professionals.domain.contato;

import com.simples.dental.professionals.domain.profissional.Profissional;
import org.junit.jupiter.api.Test;

import static com.simples.dental.professionals.UtilsConfigTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContatoTest {

    @Test
    public void givenAValidParams_whenCallNewContato_thenReturnsANewOne() {
        final var profissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var contato = Contato.newContato(EXPECTED_CONTATO_NOME, EXPECTED_CONTATO, profissional);

        assertNotNull(contato);
        assertNotNull(contato.getId());
        assertNotNull(contato.getProfissional());
        assertEquals(EXPECTED_CONTATO_NOME, contato.getNome());
        assertEquals(EXPECTED_CONTATO, contato.getContato());
        assertEquals(EXPECTED_PROFISSIONAL_NOME, contato.getProfissional().getNome());
        assertEquals(EXPECTED_CARGO, contato.getProfissional().getCargo());
        assertEquals(EXPECTED_NASCIMENTO, contato.getProfissional().getNascimento());
        assertNotNull(contato.getCreatedDate());
        assertNotNull(contato.getProfissional().getCreatedDate());
    }
}
