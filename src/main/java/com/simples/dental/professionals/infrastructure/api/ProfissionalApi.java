package com.simples.dental.professionals.infrastructure.api;

import com.simples.dental.professionals.application.profissional.ProfissionalOutput;
import com.simples.dental.professionals.infrastructure.models.CreateOrUpdateProfessionalDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ProfissionalOutput> createProfissional(@RequestBody CreateOrUpdateProfessionalDto input);

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
    ProfissionalOutput getById(@PathVariable String id);

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
    ResponseEntity<ProfissionalOutput> updateProfissional(
            @PathVariable String id,
            @RequestBody CreateOrUpdateProfessionalDto input
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
