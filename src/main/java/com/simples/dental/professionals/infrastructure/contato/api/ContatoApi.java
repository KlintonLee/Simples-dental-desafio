package com.simples.dental.professionals.infrastructure.contato.api;

import com.simples.dental.professionals.application.contato.ContatoOutput;
import com.simples.dental.professionals.infrastructure.profissional.models.CreateContatoInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ContatoOutput> createContato(@RequestBody CreateContatoInput input);

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
    ContatoOutput getById(@PathVariable String id);
}
