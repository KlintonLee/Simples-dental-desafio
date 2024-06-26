package com.simples.dental.professionals.application.contato.retrieve.list;

import com.simples.dental.professionals.domain.contato.ContatoGateway;
import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.domain.pagination.SearchQuery;
import com.simples.dental.professionals.domain.exceptions.UnprocessableFieldsException;
import com.simples.dental.professionals.infrastructure.helpers.PersistenceHelpers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.simples.dental.professionals.UtilsConfigTest.EXPECTED_QUERY_PAGE;
import static com.simples.dental.professionals.UtilsConfigTest.EXPECTED_QUERY_PER_PAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ListContatosUseCaseTest {

    @InjectMocks
    private DefaultListContatosUseCase useCase;

    @Mock
    private ContatoGateway contatoGateway;

    @Test
    public void givenAValidQuery_whenCallsListContatos_thenShouldReturnContatosPagination() {
        // Arrange
        final var fields = List.of("id", "nome", "contato");
        String[] rawItems1 = {"0f287198-d8a0-46fe-9a50-93de1f9722e6", "celular", "11999999999"};
        String[] rawItems2 = {"d999b945-742a-44c9-948f-1cd81b62abf0", "fixo", "1188888888"};
        final var expectedItemsCount = 2;

        final List<String[]> repositoryOutput = List.of(rawItems1, rawItems2);
        final var items = PersistenceHelpers.mapFieldsWithListOfStringArray(fields, repositoryOutput);
        final var query = SearchQuery.with(EXPECTED_QUERY_PAGE, EXPECTED_QUERY_PER_PAGE, "1", List.of("id", "nome", "contato"));
        final var pagination = new Pagination<>(EXPECTED_QUERY_PAGE, EXPECTED_QUERY_PER_PAGE, expectedItemsCount, items);

        when(contatoGateway.findAll(query)).thenReturn(pagination);

        // Act
        final var actualResult = useCase.execute(query);

        // Assert
        assertEquals(expectedItemsCount, actualResult.items().size());
        assertEquals(pagination, actualResult);
        assertEquals(EXPECTED_QUERY_PAGE, actualResult.currentPage());
        assertEquals(EXPECTED_QUERY_PER_PAGE, actualResult.perPage());
        assertEquals(repositoryOutput.size(), actualResult.total());
    }

    @Test
    public void givenAnInvalidField_whenCallListContatos_thenShouldThrowUnprocessableFieldsException() {
        final var query = SearchQuery.with(EXPECTED_QUERY_PAGE, EXPECTED_QUERY_PER_PAGE, "1", List.of("id", "invalid"));
        final var expectedErrorMessage = "Os fields disponívels são: id, nome, contato, profissional, created_date.";

        final var expectedException = assertThrows(UnprocessableFieldsException.class, () -> useCase.execute(query));

        assertEquals(expectedErrorMessage, expectedException.getMessage());
    }
}
