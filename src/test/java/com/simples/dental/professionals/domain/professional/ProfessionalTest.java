package com.simples.dental.professionals.domain.professional;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProfessionalTest {

    private static String nome = "John Doe";

    private static CargoProfissional cargo = CargoProfissional.DESENVOLVEDOR;

    private static Instant nascimento = LocalDate.of(1900, 12, 25).atStartOfDay(ZoneId.systemDefault()).toInstant();

    @Test
    public void givenAValidParams_whenCallCreateProfessional_thenReturnsANewProfessional() {
        final var professional = Profissional.newProfissional(nome, cargo, nascimento);

        assertNotNull(professional);
        assertNotNull(professional.getId());
        assertEquals(nome, professional.getNome());
        assertEquals(cargo, professional.getCargo());
        assertEquals(nascimento, professional.getNascimento());
        assertEquals(true, professional.isActive());
        assertNotNull(professional.getCreatedDate());
    }
}
