package com.simples.dental.professionals.application.contato.retrieve.get;

import com.simples.dental.professionals.domain.contato.Contato;
import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.contato.ContatoId;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.simples.dental.professionals.UtilsConfigTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetContatoByIdUseCaseTest {

    @InjectMocks
    private DefaultGetContatoByIdUseCase useCase;

    @Mock
    private ContatoGateway contatoGateway;

    @Test
    public void givenAValidContatoId_whenCallGetById_thenShouldReturnContatoData() {
        final var profissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var contato = Contato.newContato(EXPECTED_CONTATO_NOME, EXPECTED_CONTATO, profissional);
        final var contatoId = contato.getId();
        when(contatoGateway.findById(contatoId)).thenReturn(Optional.of(contato));

        final var actualOutput = useCase.execute(contatoId.getValue());

        assertNotNull(actualOutput);
        assertNotNull(actualOutput.profissional());
        assertEquals(EXPECTED_CONTATO_NOME, actualOutput.nome());
        assertEquals(EXPECTED_CONTATO, actualOutput.contato());
        assertEquals(profissional.getId().getValue(), actualOutput.profissional().id());
        assertEquals(profissional.getNome(), actualOutput.profissional().nome());
        assertEquals(profissional.getCargo(), actualOutput.profissional().cargo());
        assertEquals(profissional.getNascimento(), actualOutput.profissional().nascimento());
        assertNotNull(actualOutput.createdDate());
        assertNotNull(actualOutput.profissional().createdDate());
    }

    @Test
    public void givenAnInvalidProfissionalId_whenCallGetById_thenShouldReturnNotFoundException() {
        final var contatoId = ContatoId.from("123");
        final var expectedErrorMessage = "Contato com ID 123 não foi encontrado";
        when(contatoGateway.findById(contatoId)).thenReturn(Optional.empty());

        final var actualException = assertThrows(
                NotFoundException.class,
                () -> useCase.execute(contatoId.getValue())
        );

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAValidContatoId_whenCallGetByIdThrowsAnUnexpectedError_thenShouldReturnException() {
        final var profissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var contato = Contato.newContato(EXPECTED_CONTATO_NOME, EXPECTED_CONTATO, profissional);
        final var contatoId = contato.getId();
        final var expectedErrorMessage = "Gateway Error";
        when(contatoGateway.findById(contatoId)).thenThrow(new IllegalStateException("Gateway Error"));

        final var actualException = assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(contatoId.getValue())
        );

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
