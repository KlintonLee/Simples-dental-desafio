package com.simples.dental.professionals.infrastructure.helpers;

import com.simples.dental.professionals.domain.exceptions.InvalidPaginationValuesException;
import com.simples.dental.professionals.domain.profissional.Profissional;
import com.simples.dental.professionals.domain.exceptions.UnprocessableEntityException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.simples.dental.professionals.infrastructure.helpers.ControllerHelpers.*;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerHelpersTest {

    @Test
    public void givenAValidDateString_whenCallformatDate_thenShouldReturnLocalDate() {
        final var expectedDate = LocalDate.of(2000, 12, 31);
        final var date = formatDate("31-12-2000");

        assertEquals(expectedDate.getDayOfMonth(), date.getDayOfMonth());
        assertEquals(expectedDate.getMonth(), date.getMonth());
        assertEquals(expectedDate.getYear(), date.getYear());
    }

    @Test
    public void givenAnInvalidDateString_whenCallformatDate_thenShouldThrownUnprocessableEntityException() {
        final var expectedErrorMessage = "O formato da data deve ser dd-MM-yyyy, por exemplo: 15-02-2000";

        final var actualException = assertThrows(UnprocessableEntityException.class, () ->  formatDate("2000-12-31"));

        assertEquals(expectedErrorMessage, actualException.getMessage());
    }

    @Test
    public void givenANullFields_whenCallFieldsMapper_thenShouldReturnAllEntityFields() {
        final var actualFields = fieldsMapper(Profissional.class, null);

        assertTrue(actualFields.contains("id"));
        assertTrue(actualFields.contains("nome"));
        assertTrue(actualFields.contains("cargo"));
        assertTrue(actualFields.contains("active"));
        assertTrue(actualFields.contains("nascimento"));
        assertTrue(actualFields.contains("created_date"));
    }

    @Test
    public void givenAListWithNomeField_whenCallFieldsMapper_thenShouldReturnWithIdExtraField() {
        final var fields = new ArrayList<String>();
        fields.add("nome");
        final var actualFields = fieldsMapper(Profissional.class, fields);

        assertTrue(actualFields.contains("id"));
        assertTrue(actualFields.contains("nome"));
    }

    @Test
    public void givenValidPageAndPerPage_whenCallValidatePaginationValues_thenShouldBeOk() {
        final var page = 1;
        final var perPage = 10;

        assertDoesNotThrow(() -> validatePaginationValues(page, perPage));
    }

    @Test
    public void givenThePageAsZero_whenCallValidatePaginationValues_thenShouldThrowInvalidPaginationValuesException() {
        final var page = 0;
        final var perPage = 10;
        final var expectedErrorMessage = "A página não pode ser zero ou negativo";

        final var exception = assertThrows(InvalidPaginationValuesException.class, () -> validatePaginationValues(page, perPage));

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void givenThePageAsNegative_whenCallValidatePaginationValues_thenShouldThrowInvalidPaginationValuesException() {
        final var page = -1;
        final var perPage = 10;
        final var expectedErrorMessage = "A página não pode ser zero ou negativo";

        final var exception = assertThrows(InvalidPaginationValuesException.class, () -> validatePaginationValues(page, perPage));

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    public void givenThePerPageAsNegative_whenCallValidatePaginationValues_thenShouldThrowInvalidPaginationValuesException() {
        final var page = 1;
        final var perPage = -1;
        final var expectedErrorMessage = "Os resultados por página não deve ser negativo";

        final var exception = assertThrows(InvalidPaginationValuesException.class, () -> validatePaginationValues(page, perPage));

        assertEquals(expectedErrorMessage, exception.getMessage());
    }
}
