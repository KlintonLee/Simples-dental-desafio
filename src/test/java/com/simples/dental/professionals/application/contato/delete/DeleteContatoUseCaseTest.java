package com.simples.dental.professionals.application.contato.delete;

import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.contato.ContatoId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteContatoUseCaseTest {

    @InjectMocks
    private DefaultDeleteContatoUseCase useCase;

    @Mock
    private ContatoGateway contatoGateway;

    @Test
    public void givenAValidId_whenCallDeleteContato_thenShouldDeleteIt() {
        final var contatoId = ContatoId.from("any_valid_id");
        when(contatoGateway.existsById(contatoId)).thenReturn(true);
        doNothing().when(contatoGateway).deleteById(contatoId);

        useCase.execute(contatoId.getValue());

        verify(contatoGateway, times(1)).deleteById(contatoId);
    }

    @Test
    public void givenAnInvalidId_whenCallDeleteContato_thenShouldBeOk() {
        final var contatoId = ContatoId.from("any_invalid_id");
        when(contatoGateway.existsById(contatoId)).thenReturn(false);

        useCase.execute(contatoId.getValue());

        verify(contatoGateway, times(0)).deleteById(any());
    }

}
