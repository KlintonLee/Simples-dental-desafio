package com.simples.dental.professionals.application.profissional.create;

import com.simples.dental.professionals.domain.profissional.CargoProfissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateProfissionalUseCaseTest {

    private static String EXPECTED_NOME = "John Doe";

    private static CargoProfissional EXPECTED_CARGO = CargoProfissional.DESENVOLVEDOR;

    private static Instant EXPECTED_NASCIMENTO = LocalDate.of(1900, 12, 25).atStartOfDay(ZoneId.systemDefault()).toInstant();
    @InjectMocks
    private DefaultCreateProfissionalUseCase useCase;

    @Mock
    private ProfissionalGateway profissionalGateway;

    @Test
    public void givenAValidParams_whenCallCreationUseCase_thenShouldReturnANewProfissional() {
        final var command = CreateProfissionalCommand.with(EXPECTED_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        when(profissionalGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(command);

        assertNotNull(actualOutput);
        assertEquals(EXPECTED_NOME, actualOutput.nome());
        assertEquals(EXPECTED_CARGO, actualOutput.cargo());
        assertEquals(EXPECTED_NASCIMENTO, actualOutput.nascimento());
        assertTrue(actualOutput.active());
        assertNotNull(actualOutput.createdDate());
        verify(profissionalGateway, times(1)).create(argThat(profissional ->
                Objects.equals(EXPECTED_NOME, profissional.getNome())
                && Objects.equals(EXPECTED_CARGO, profissional.getCargo())
                && Objects.equals(EXPECTED_NASCIMENTO, profissional.getNascimento())
                && profissional.isActive()
                && Objects.nonNull(profissional.getCreatedDate())
        ));
    }
}