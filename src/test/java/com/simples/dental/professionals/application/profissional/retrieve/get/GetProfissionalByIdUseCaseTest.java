package com.simples.dental.professionals.application.profissional.retrieve.get;

import com.simples.dental.professionals.exceptions.NotFoundException;
import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.simples.dental.professionals.application.UtilsConfigTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetProfissionalByIdUseCaseTest {

    @InjectMocks
    private DefaultGetProfissionalByIdUseCase useCase;

    @Mock
    private ProfissionalGateway profissionalGateway;

    @Test
    public void givenAValidProfissionalId_whenCallGetById_thenShouldReturnProfissionalData() {
        final var profissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var profissionalId = profissional.getId();
        when(profissionalGateway.findById(profissionalId)).thenReturn(Optional.of(profissional));

        final var actualOutput = useCase.execute(profissionalId.getValue());

        assertNotNull(actualOutput);
        assertNotNull(actualOutput.id());
        assertEquals(EXPECTED_PROFISSIONAL_NOME, actualOutput.nome());
        assertEquals(EXPECTED_CARGO, actualOutput.cargo());
        assertEquals(EXPECTED_NASCIMENTO, actualOutput.nascimento());
        assertTrue(actualOutput.active());
        assertNotNull(actualOutput.createdDate());
    }

    @Test
    public void givenAnInvalidProfissionalId_whenCallGetById_thenShouldReturnNotFoundException() {
        final var profissionalId = IdProfissional.from("invalid");
        final var expectedErrorMessage = "Profissional com ID invalid não foi encontrado";
        when(profissionalGateway.findById(profissionalId)).thenReturn(Optional.empty());

        final var actualException = assertThrows(
                NotFoundException.class,
                () -> useCase.execute(profissionalId.getValue())
        );

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenAValidProfissionalId_whenCallGetByIdThrowsAnUnexpectedError_thenShouldReturnException() {
        final var profissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var profissionalId = profissional.getId();
        final var expectedErrorMessage = "Gateway Error";
        when(profissionalGateway.findById(profissionalId)).thenThrow(new IllegalStateException("Gateway Error"));

        final var actualException = assertThrows(
                IllegalStateException.class,
                () -> useCase.execute(profissionalId.getValue())
        );

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }
}
