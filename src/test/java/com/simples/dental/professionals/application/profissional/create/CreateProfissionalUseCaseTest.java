package com.simples.dental.professionals.application.profissional.create;

import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import com.simples.dental.professionals.domain.exceptions.UnprocessableEntityException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static com.simples.dental.professionals.UtilsConfigTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateProfissionalUseCaseTest {


    @InjectMocks
    private DefaultCreateProfissionalUseCase useCase;

    @Mock
    private ProfissionalGateway profissionalGateway;

    @Test
    public void givenAValidParams_whenCallCreationUseCase_thenShouldReturnANewProfissional() {
        final var command = CreateProfissionalCommand.with(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        when(profissionalGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(command);

        assertNotNull(actualOutput);
        assertEquals(EXPECTED_PROFISSIONAL_NOME, actualOutput.nome());
        assertEquals(EXPECTED_CARGO, actualOutput.cargo());
        assertEquals(EXPECTED_NASCIMENTO, actualOutput.nascimento());
        assertTrue(actualOutput.active());
        assertNotNull(actualOutput.createdDate());
        verify(profissionalGateway, times(1)).create(argThat(profissional ->
                Objects.equals(EXPECTED_PROFISSIONAL_NOME, profissional.getNome())
                && Objects.equals(EXPECTED_CARGO, profissional.getCargo())
                && Objects.equals(EXPECTED_NASCIMENTO, profissional.getNascimento())
                && profissional.isActive()
                && Objects.nonNull(profissional.getCreatedDate())
        ));
    }

    @Test
    public void givenAnInvalidNullName_whenCallCreationUseCase_thenShouldThrownAnException() {
        final var expectedErrorMessage = "O 'nome' do contato não pode ser nulo ou vazio";
        final var command = CreateProfissionalCommand.with(null, EXPECTED_CARGO, EXPECTED_NASCIMENTO);

        final var actualException = assertThrows(
                UnprocessableEntityException.class,
                () -> useCase.execute(command));

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallCreationUseCase_thenShouldThrownAnException() {
        final var expectedErrorMessage = "O 'nome' do contato não pode ser nulo ou vazio";
        final var command = CreateProfissionalCommand.with(" ", EXPECTED_CARGO, EXPECTED_NASCIMENTO);

        final var actualException = assertThrows(
                UnprocessableEntityException.class,
                () -> useCase.execute(command));

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAnInvalidLongName_whenCallCreationUseCase_thenShouldThrownAnException() {
        final var longName = "a".repeat(256);
        final var expectedErrorMessage = "O 'nome' não deve ser maior do que 255";
        final var command = CreateProfissionalCommand.with(longName, EXPECTED_CARGO, EXPECTED_NASCIMENTO);

        final var actualException = assertThrows(
                UnprocessableEntityException.class,
                () -> useCase.execute(command));

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
