package com.simples.dental.professionals.domain.profissional;

import com.simples.dental.professionals.exceptions.UnprocessableEntityException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ProfissionalTest {

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

    @Test
    public void givenAnInvalidParams_whenCallNewProfissionalWithNullName_thenShouldThrownAnUnprocessableEntityException() {
        final var professional = Profissional.newProfissional(null, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var expectedErrorMessage = "O 'nome' do contato não pode ser nulo ou vazio";

        final var actualException = assertThrows(
                UnprocessableEntityException.class,
                professional::validate);

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAnInvalidParams_whenCallNewProfissionalWithEmptyName_thenShouldThrownAnUnprocessableEntityException() {
        final var professional = Profissional.newProfissional(" ", EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var expectedErrorMessage = "O 'nome' do contato não pode ser nulo ou vazio";

        final var actualException = assertThrows(
                UnprocessableEntityException.class,
                professional::validate);

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAnInvalidParams_whenCallNewProfissionalWithNameGreatherThan255_thenShouldThrownAnUnprocessableEntityException() {
        final var longName = "a".repeat(256);
        final var professional = Profissional.newProfissional(longName, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var expectedErrorMessage = "O 'nome' não deve ser maior do que 255";
        final var actualException = assertThrows(
                UnprocessableEntityException.class,
                professional::validate);

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
