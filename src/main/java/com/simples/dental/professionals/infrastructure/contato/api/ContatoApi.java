package com.simples.dental.professionals.infrastructure.contato.api;

import com.simples.dental.professionals.domain.pagination.Pagination;
import com.simples.dental.professionals.infrastructure.contato.models.CreateContatoInput;
import com.simples.dental.professionals.infrastructure.contato.models.ContatoResponse;
import com.simples.dental.professionals.infrastructure.contato.models.UpdateContatoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("contatos")
@Tag(name = "Contatos")
public interface ContatoApi {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Cria um novo contato")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contato criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Um erro de validação foi lançado"),
            @ApiResponse(responseCode = "500", description = "Um erro inexperado ocorreu no servidor")
    })
    ResponseEntity<ContatoResponse> createContato(@RequestBody CreateContatoInput input);

    @GetMapping(
            value = "{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Busca um contato pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contato localizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Contato não localizado"),
            @ApiResponse(responseCode = "500", description = "Um erro inexperado ocorreu no servidor")
    })
    ContatoResponse getById(@PathVariable String id);

    @GetMapping
    @Operation(summary = "Lista todos contatos paginado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Um erro de validação dos parâmetros foi lançado"),
            @ApiResponse(responseCode = "500", description = "Um erro inexperado ocorreu no servidor"),
    })
    Pagination<Map<String, String>> listContatos(
            @RequestParam(name = "page", required = false, defaultValue = "1") final int page,
            @RequestParam(name = "perPage", required = false, defaultValue = "10") final int perPage,
            @RequestParam(name = "q", required = false, defaultValue = "") final String q,
            @RequestParam(name = "fields", required = false) final List<String> fields
    );

    @PutMapping(
            value = "{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(summary = "Atualiza um contato pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contato atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Contato ou Profissional não localizado"),
            @ApiResponse(responseCode = "422", description = "Um erro de validação foi lançado"),
            @ApiResponse(responseCode = "500", description = "Um erro inexperado ocorreu no servidor")
    })
    ResponseEntity<ContatoResponse> updateContato(
            @PathVariable String id,
            @RequestBody UpdateContatoInput input
    );

    @DeleteMapping(value = "{id}")
    @Operation(summary = "Deleta um contato pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Contato deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Contato não localizado"),
            @ApiResponse(responseCode = "500", description = "Um erro inexperado ocorreu no servidor")
    })
    ResponseEntity<Void> deleteContato(@PathVariable String id);
}
