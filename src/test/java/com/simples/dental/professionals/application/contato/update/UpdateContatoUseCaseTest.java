package com.simples.dental.professionals.application.contato.update;

import com.simples.dental.professionals.domain.contato.Contato;
import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.contato.ContatoId;
import com.simples.dental.professionals.domain.profissional.CargoProfissional;
import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import com.simples.dental.professionals.domain.exceptions.NotFoundException;
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
public class UpdateContatoUseCaseTest {

    @InjectMocks
    private DefaultUpdateContatoUseCase useCase;

    @Mock
    private ContatoGateway contatoGateway;

    @Mock
    private ProfissionalGateway profissionalGateway;

    @Test
    public void givenAValidId_whenCallUpdate_thenShouldReturnUpdatedContato() {
        final var oldProfissional = Profissional.newProfissional("any", CargoProfissional.DESIGNER, EXPECTED_NASCIMENTO);
        final var expectedProfissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var expectedProfissionalId = expectedProfissional.getId();
        final var newContato = Contato.newContato("any", "123", oldProfissional);
        final var contatoId = newContato.getId();
        final var command = UpdateContatoCommand.with(contatoId.getValue(), EXPECTED_CONTATO_NOME, EXPECTED_CONTATO, expectedProfissionalId.getValue());
        when(contatoGateway.findById(contatoId)).thenReturn(Optional.of(newContato));
        when(profissionalGateway.findById(expectedProfissionalId)).thenReturn(Optional.of(expectedProfissional));
        when(contatoGateway.update(any())).thenAnswer(returnsFirstArg());

        final var actualContato = useCase.execute(command);

        assertNotNull(actualContato);
        verify(contatoGateway, times(1)).update(argThat(updatedContato ->
                Objects.equals(EXPECTED_CONTATO_NOME, updatedContato.getNome())
                && Objects.equals(EXPECTED_CONTATO, updatedContato.getContato())
                && Objects.equals(expectedProfissional, updatedContato.getProfissional())
                && Objects.equals(newContato.getCreatedDate(), updatedContato.getCreatedDate())
        ));
    }

    @Test void givenANonExistingContato_whenCallUpdate_thenShouldThrownANotFoundException() {
        final var profissionalId = IdProfissional.from("any_valid");
        final var contatoId = ContatoId.from("123_invalid");
        final var command = UpdateContatoCommand.with(contatoId.getValue(), EXPECTED_CONTATO_NOME, EXPECTED_CONTATO, profissionalId.getValue());
        when(contatoGateway.findById(contatoId)).thenReturn(Optional.empty());
        String expectedErrorMessage = "Contato com ID 123_invalid não foi encontrado";

        final var actualException = assertThrows(NotFoundException.class, () -> useCase.execute(command));

        assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(contatoGateway, times(0)).update(any());
    }

    @Test void givenANonExistingProfissional_whenCallUpdate_thenShouldThrownANotFoundException() {
        final var profissionalId = IdProfissional.from("any_invalid");
        final var profissional = Profissional.newProfissional(EXPECTED_PROFISSIONAL_NOME, EXPECTED_CARGO, EXPECTED_NASCIMENTO);
        final var newContato = Contato.newContato("any", "123", profissional);
        final var contatoId = newContato.getId();
        final var command = UpdateContatoCommand.with(contatoId.getValue(), EXPECTED_CONTATO_NOME, EXPECTED_CONTATO, profissionalId.getValue());
        when(contatoGateway.findById(contatoId)).thenReturn(Optional.of(newContato));
        when(profissionalGateway.findById(profissionalId)).thenReturn(Optional.empty());
        final var expectedErrorMessage = "Profissional com ID any_invalid não foi encontrado";

        final var actualException = assertThrows(NotFoundException.class, () -> useCase.execute(command));

        assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(contatoGateway, times(0)).update(any());
    }
}
