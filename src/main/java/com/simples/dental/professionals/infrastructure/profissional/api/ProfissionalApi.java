package com.simples.dental.professionals.infrastructure.profissional.api;

import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.infrastructure.profissional.models.CreateProfissionalInput;
import com.simples.dental.professionals.infrastructure.profissional.models.ProfissionalResponse;
import com.simples.dental.professionals.infrastructure.profissional.models.UpdateProfissionalInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("profissionais")
@Tag(name = "Profissionais")
public interface ProfissionalApi {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Cria um novo profissional")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profissional criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Um erro de validação foi lançado"),
            @ApiResponse(responseCode = "500", description = "Um erro inexperado ocorreu no servidor")
    })
    public ResponseEntity<ProfissionalResponse> createProfissional(@RequestBody CreateProfissionalInput input);

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Busca um profissional pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profissional localizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Profisional não localizado"),
            @ApiResponse(responseCode = "500", description = "Um erro inexperado ocorreu no servidor")
    })
    ProfissionalResponse getById(@PathVariable String id);

    @GetMapping
    @Operation(summary = "Lista todos profissionais paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Um erro de validação dos parâmetros foi lançado"),
            @ApiResponse(responseCode = "500", description = "Um erro inexperado ocorreu no servidor"),
    })
    Pagination<Map<String, String>> listProfissionais(
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "q", required = false, defaultValue = "") final String q,
            @RequestParam(name = "fields", required = false) final List<String> fields
    );

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Atualiza um profissional pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profissional atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Profissional não localizado"),
            @ApiResponse(responseCode = "422", description = "Um erro de validação foi lançado"),
            @ApiResponse(responseCode = "500", description = "Um erro inexperado ocorreu no servidor")
    })
    ResponseEntity<ProfissionalResponse> updateProfissional(
            @PathVariable String id,
            @RequestBody UpdateProfissionalInput input
    );

    @DeleteMapping(value = "{id}")
    @Operation(summary = "Deleta um profissional pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Profissional deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Profissional não localizado"),
            @ApiResponse(responseCode = "500", description = "Um erro inexperado ocorreu no servidor")
    })
    ResponseEntity<Void> deleteProfissional(@PathVariable String id);
}
