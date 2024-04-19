package com.simples.dental.professionals.application.profissional.update;

import com.simples.dental.professionals.exceptions.NotFoundException;
import com.simples.dental.professionals.domain.profissional.CargoProfissional;
import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static com.simples.dental.professionals.application.profissional.UtilsConfigTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateProfissionalUseCaseTest {

    @InjectMocks
    private DefaultUpdateProfissionalUseCase useCase;

    @Mock
    private ProfissionalGateway profissionalGateway;

    @Test
    public void givenAValidId_whenCallUpdate_thenShouldReturnUpdatedProfissional() {
        final var profissional = Profissional.newProfissional("john", CargoProfissional.SUPORTE, LocalDate.now());
        final var profissionalId = profissional.getId();
        when(profissionalGateway.findById(profissionalId)).thenReturn(Optional.of(profissional));
        when(profissionalGateway.update(any())).thenAnswer(returnsFirstArg());
        final var command = UpdateProfissionalCommand.with(profissionalId.getValue(), EXPECTED_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);

        final var actualOutput = useCase.execute(command);

        assertNotNull(actualOutput);
        assertEquals(EXPECTED_NOME, actualOutput.nome());
        assertEquals(EXPECTED_CARGO, actualOutput.cargo());
        assertEquals(EXPECTED_NASCIMENTO, actualOutput.nascimento());
        assertTrue(actualOutput.active());
        assertNotNull(actualOutput.createdDate());
    }

    @Test
    public void givenAnInvalidId_whenCallUpdate_thenShouldReturnNotFoundException() {
        final var profissionalId = IdProfissional.from("invalid");
        final var expectedErrorMessage = "Profissional com ID invalid não foi encontrado";
        when(profissionalGateway.findById(profissionalId)).thenReturn(Optional.empty());

        final var command = UpdateProfissionalCommand.with(profissionalId.getValue(), EXPECTED_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);

        final var actualException = assertThrows(NotFoundException.class, () -> useCase.execute(command));

        assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(profissionalGateway, times(0)).update(any());
    }
}
