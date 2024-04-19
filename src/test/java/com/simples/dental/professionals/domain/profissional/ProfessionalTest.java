package com.simples.dental.professionals.domain.profissional;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProfessionalTest {

    private static String EXPECTED_NOME = "John Doe";

    private static CargoProfissional EXPECTED_CARGO = CargoProfissional.DESENVOLVEDOR;

    private static LocalDate EXPECTED_NASCIMENTO = LocalDate.of(1900, 12, 25);

    @Test
    public void givenAValidParams_whenCallNewProfissional_thenReturnsANewOne() {
        final var professional = Profissional.newProfissional(EXPECTED_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var expectedActive = true;

        assertNotNull(professional);
        assertNotNull(professional.getId());
        assertEquals(EXPECTED_NOME, professional.getNome());
        assertEquals(EXPECTED_CARGO, professional.getCargo());
        assertEquals(EXPECTED_NASCIMENTO, professional.getNascimento());
        assertEquals(expectedActive, professional.isActive());
        assertNotNull(professional.getCreatedDate());
    }

    @Test
    public void givenAnActiveProfissional_whenCallDeactive_thenShouldReturnItDeactived() {
        final var professional = Profissional.newProfissional(EXPECTED_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var expectedActive = false;

        professional.deactivate();

        assertNotNull(professional);
        assertNotNull(professional.getId());
        assertEquals(EXPECTED_NOME, professional.getNome());
        assertEquals(EXPECTED_CARGO, professional.getCargo());
        assertEquals(EXPECTED_NASCIMENTO, professional.getNascimento());
        assertEquals(expectedActive, professional.isActive());
        assertNotNull(professional.getCreatedDate());
    }
}
