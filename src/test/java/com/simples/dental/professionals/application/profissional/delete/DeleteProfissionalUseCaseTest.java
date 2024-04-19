package com.simples.dental.professionals.application.profissional.delete;

import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static com.simples.dental.professionals.UtilsConfigTest.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteProfissionalUseCaseTest {

    @InjectMocks
    private DefaultDeleteProfissionalUseCase useCase;

    @Mock
    private ProfissionalGateway profissionalGateway;

    @Test
    public void givenAValidId_whenCallDeleteProfissional_thenShouldExecuteLogicalDeletion() {
        final var profissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var profissionalId = profissional.getId();
        when(profissionalGateway.findById(profissionalId)).thenReturn(Optional.of(profissional));
        when(profissionalGateway.update(any())).thenAnswer(returnsFirstArg());

        useCase.execute(profissionalId.getValue());

        verify(profissionalGateway, times(1)).update(argThat(output ->
                Objects.equals(profissional.getId(), output.getId())
                && Objects.equals(profissional.getNome(), output.getNome())
                && Objects.equals(profissional.getCargo(), output.getCargo())
                && Objects.equals(profissional.getNascimento(), output.getNascimento())
                && !output.isActive()
                && Objects.equals(profissional.getCreatedDate(), output.getCreatedDate())
        ));
    }

    @Test
    public void givenAnInvalidId_whenCallDeleteProfissional_thenShouldBeOk() {
        final var profissionalId = IdProfissional.from("invalid");
        when(profissionalGateway.findById(profissionalId)).thenReturn(Optional.empty());

        useCase.execute(profissionalId.getValue());

        verify(profissionalGateway, times(0)).update(any());
    }
}
