package com.simples.dental.professionals.domain.contato;

import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.exceptions.UnprocessableEntityException;
import org.junit.jupiter.api.Test;

import static com.simples.dental.professionals.UtilsConfigTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void givenAnInvalidParams_whenCallNewContatoWithNullName_thenShouldThrownAnUnprocessableEntityException() {
        final var profissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var contato = Contato.newContato(null, EXPECTED_CONTATO, profissional);
        final var expectedErrorMessage = "O 'nome' do contato não pode ser nulo ou vazio";

        final var actualException = assertThrows(
                UnprocessableEntityException.class,
                contato::validate);

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAnInvalidParams_whenCallNewContatoWithEmptyName_thenShouldThrownAnUnprocessableEntityException() {
        final var profissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var contato = Contato.newContato(" ", EXPECTED_CONTATO, profissional);
        final var expectedErrorMessage = "O 'nome' do contato não pode ser nulo ou vazio";

        final var actualException = assertThrows(
                UnprocessableEntityException.class,
                contato::validate);

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAnInvalidParams_whenCallNewContatoWithNameGreatherThan255_thenShouldThrownAnUnprocessableEntityException() {
        final var longName = "a".repeat(256);
        final var profissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var contato = Contato.newContato(longName, EXPECTED_CONTATO, profissional);
        final var expectedErrorMessage = "O 'nome' não deve ser maior do que 255";
        final var actualException = assertThrows(
                UnprocessableEntityException.class,
                contato::validate);

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
