package com.simples.dental.professionals.domain.professional;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProfessionalTest {

    private static String EXPECTED_NOME = "John Doe";

    private static CargoProfissional EXPECTED_CARGO = CargoProfissional.DESENVOLVEDOR;

    private static Instant EXPECTED_NASCIMENTO = LocalDate.of(1900, 12, 25).atStartOfDay(ZoneId.systemDefault()).toInstant();

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
}
