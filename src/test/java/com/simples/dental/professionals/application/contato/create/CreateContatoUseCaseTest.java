package com.simples.dental.professionals.application.contato.create;

import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static com.simples.dental.professionals.application.UtilsConfigTest.*;
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
        final var newProfissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var expectedProfissionalId = newProfissional.getId();
        final var command = CreateContatoCommand.with(EXPECTED_CONTATO_NOME, EXPECTED_CONTATO, expectedProfissionalId.getValue());
        when(profissionalGateway.existsById(expectedProfissionalId)).thenReturn(true);
        when(contatoGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(command);

        assertNotNull(actualOutput);
        assertEquals(EXPECTED_CONTATO_NOME, actualOutput.nome());
        assertEquals(EXPECTED_CONTATO, actualOutput.contato());
        assertEquals(expectedProfissionalId.getValue(), actualOutput.profissionalId());
        assertNotNull(actualOutput.createdDate());

        verify(contatoGateway, times(1)).create(argThat(contato ->
                Objects.equals(EXPECTED_CONTATO_NOME, contato.getNome())
                        && Objects.equals(EXPECTED_CONTATO, contato.getContato())
                        && Objects.equals(expectedProfissionalId.getValue(), contato.getProfissionalId())
                        && Objects.nonNull(contato.getCreatedDate())
        ));
    }
}
