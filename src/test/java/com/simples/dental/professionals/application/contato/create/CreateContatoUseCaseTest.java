package com.simples.dental.professionals.application.contato.create;

import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import com.simples.dental.professionals.exceptions.NotFoundException;
import com.simples.dental.professionals.exceptions.UnprocessableEntityException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static com.simples.dental.professionals.UtilsConfigTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateContatoUseCaseTest {

    @InjectMocks
    private DefaultCreateContatoUseCase useCase;

    @Mock
    private ContatoGateway contatoGateway;

    @Mock
    private ProfissionalGateway profissionalGateway;

    @Test
    public void givenAValidParams_whenCallCreationUseCase_thenShouldReturnANewContato() {
        final var expectedProfissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var expectedProfissionalId = expectedProfissional.getId();
        final var command = CreateContatoCommand.with(EXPECTED_CONTATO_NOME, EXPECTED_CONTATO, expectedProfissionalId.getValue());
        when(profissionalGateway.findById(expectedProfissionalId)).thenReturn(Optional.of(expectedProfissional));
        when(contatoGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(command);

        assertNotNull(actualOutput);
        assertNotNull(actualOutput.profissional());
        assertEquals(EXPECTED_CONTATO_NOME, actualOutput.nome());
        assertEquals(EXPECTED_CONTATO, actualOutput.contato());
        assertEquals(expectedProfissionalId.getValue(), actualOutput.profissional().id());
        assertEquals(expectedProfissional.getNome(), actualOutput.profissional().nome());
        assertEquals(expectedProfissional.getCargo(), actualOutput.profissional().cargo());
        assertEquals(expectedProfissional.getNascimento(), actualOutput.profissional().nascimento());
        assertNotNull(actualOutput.createdDate());
        assertNotNull(actualOutput.profissional().createdDate());

        verify(contatoGateway, times(1)).create(argThat(contato ->
                Objects.equals(EXPECTED_CONTATO_NOME, contato.getNome())
                        && Objects.equals(EXPECTED_CONTATO, contato.getContato())
                        && Objects.equals(expectedProfissional, contato.getProfissional())
                        && Objects.nonNull(contato.getCreatedDate())
        ));
    }

    @Test
    public void givenANonExistingProfissionalId_whenCallCreationUseCase_thenThrowNotFoundException() {
        final var expectedProfissionalId = IdProfissional.from("123");
        final var command = CreateContatoCommand.with(EXPECTED_CONTATO_NOME, EXPECTED_CONTATO, expectedProfissionalId.getValue());
        when(profissionalGateway.findById(expectedProfissionalId)).thenReturn(Optional.empty());
        final var expectedErrorMessage = "Profissional com ID 123 não foi encontrado";

        final var actualException = assertThrows(NotFoundException.class, () -> useCase.execute(command));

        assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(contatoGateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidNullName_whenCallCreationUseCase_thenThrowUnprocessableEntityException() {
        final var expectedProfissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var expectedProfissionalId = expectedProfissional.getId();
        final var command = CreateContatoCommand.with(null, EXPECTED_CONTATO, expectedProfissionalId.getValue());
        final var expectedErrorMessage = "O 'nome' do contato não pode ser nulo ou vazio";
        when(profissionalGateway.findById(expectedProfissionalId)).thenReturn(Optional.of(expectedProfissional));

        final var actualException = assertThrows(UnprocessableEntityException.class, () -> useCase.execute(command));

        assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(contatoGateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallCreationUseCase_thenThrowUnprocessableEntityException() {
        final var expectedProfissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var expectedProfissionalId = expectedProfissional.getId();
        final var command = CreateContatoCommand.with(" ", EXPECTED_CONTATO, expectedProfissionalId.getValue());
        final var expectedErrorMessage = "O 'nome' do contato não pode ser nulo ou vazio";
        when(profissionalGateway.findById(expectedProfissionalId)).thenReturn(Optional.of(expectedProfissional));

        final var actualException = assertThrows(UnprocessableEntityException.class, () -> useCase.execute(command));

        assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(contatoGateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidLongName_whenCallCreationUseCase_thenThrowUnprocessableEntityException() {
        final var expectedProfissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var expectedProfissionalId = expectedProfissional.getId();
        final var command = CreateContatoCommand.with("a".repeat(256), EXPECTED_CONTATO, expectedProfissionalId.getValue());
        final var expectedErrorMessage = "O 'nome' não deve ser maior do que 255";
        when(profissionalGateway.findById(expectedProfissionalId)).thenReturn(Optional.of(expectedProfissional));

        final var actualException = assertThrows(UnprocessableEntityException.class, () -> useCase.execute(command));

        assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(contatoGateway, times(0)).create(any());
    }
}
