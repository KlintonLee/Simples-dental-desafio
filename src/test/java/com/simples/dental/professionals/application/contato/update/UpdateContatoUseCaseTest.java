package com.simples.dental.professionals.application.contato.update;

import com.simples.dental.professionals.domain.contato.Contato;
import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.contato.ContatoId;
import com.simples.dental.professionals.domain.profissional.IdProfissional;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import com.simples.dental.professionals.exceptions.NotFoundException;
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
        final var profissionalId = IdProfissional.from("any_valid");
        final var newContato = Contato.newContato("any", "123", IdProfissional.unique().getValue());
        final var contatoId = newContato.getId();
        final var command = UpdateContatoCommand.with(contatoId.getValue(), EXPECTED_CONTATO_NOME, EXPECTED_CONTATO, profissionalId.getValue());
        when(contatoGateway.findById(contatoId)).thenReturn(Optional.of(newContato));
        when(contatoGateway.update(any())).thenAnswer(returnsFirstArg());
        when(profissionalGateway.existsById(profissionalId)).thenReturn(true);

        final var actualContato = useCase.execute(command);

        assertNotNull(actualContato);
        verify(contatoGateway, times(1)).update(argThat(updatedContato ->
                Objects.equals(EXPECTED_CONTATO_NOME, updatedContato.getNome())
                && Objects.equals(EXPECTED_CONTATO, updatedContato.getContato())
                && Objects.equals(profissionalId.getValue(), updatedContato.getProfissionalId())
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
        final var profissionalId = IdProfissional.from("any_valid");
        final var newContato = Contato.newContato("any", "123", IdProfissional.unique().getValue());
        final var contatoId = newContato.getId();
        final var command = UpdateContatoCommand.with(contatoId.getValue(), EXPECTED_CONTATO_NOME, EXPECTED_CONTATO, profissionalId.getValue());
        when(contatoGateway.findById(contatoId)).thenReturn(Optional.of(newContato));
        when(profissionalGateway.existsById(profissionalId)).thenReturn(false);
        final var expectedErrorMessage = "Profissional com ID any_valid não foi encontrado";

        final var actualException = assertThrows(NotFoundException.class, () -> useCase.execute(command));

        assertEquals(expectedErrorMessage, actualException.getMessage());
        verify(contatoGateway, times(0)).update(any());
    }
}
