package com.simples.dental.professionals.application.profissional.retrieve.list;

import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.domain.pagination.SearchQuery;
import com.simples.dental.professionals.domain.profissional.ProfissionalGateway;
import com.simples.dental.professionals.exceptions.UnprocessableFieldsException;
import com.simples.dental.professionals.infrastructure.configuration.DatabaseHelpers;
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
public class ListProfissionaisUseCaseTest {

    @InjectMocks
    private DefaultListProfissionaisUseCase useCase;

    @Mock
    private ProfissionalGateway profissionalGateway;

    @Test
    public void givenAValidQuery_whenCallsListProfissionais_thenShouldReturnProfissionaisPagination() {
        // Arrange
        final var fields = List.of("id", "nome", "cargo");
        String[] rawItems1 = {"0f287198-d8a0-46fe-9a50-93de1f9722e6", "celular", "DEVELOPER"};
        String[] rawItems2 = {"d999b945-742a-44c9-948f-1cd81b62abf0", "fixo", "TESTER"};
        final var expectedItemsCount = 2;

        final List<String[]> repositoryOutput = List.of(rawItems1, rawItems2);
        final var items = DatabaseHelpers.mapFieldsWithListOfStringArray(fields, repositoryOutput);
        final var query = SearchQuery.with(EXPECTED_QUERY_PAGE, EXPECTED_QUERY_PER_PAGE, "1", List.of("id", "nome", "cargo"));
        final var pagination = new Pagination<>(EXPECTED_QUERY_PAGE, EXPECTED_QUERY_PER_PAGE, expectedItemsCount, items);

        when(profissionalGateway.findAll(query)).thenReturn(pagination);

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
    public void givenAnInvalidField_whenCallListProfissionais_thenShouldThrowUnprocessableFieldsException() {
        final var query = SearchQuery.with(EXPECTED_QUERY_PAGE, EXPECTED_QUERY_PER_PAGE, "1", List.of("id", "invalid"));
        final var expectedErrorMessage = "Os fields disponívels são: id, nome, cargo, nascimento, active.";

        final var expectedException = assertThrows(UnprocessableFieldsException.class, () -> useCase.execute(query));

        assertEquals(expectedErrorMessage, expectedException.getMessage());
    }
}
