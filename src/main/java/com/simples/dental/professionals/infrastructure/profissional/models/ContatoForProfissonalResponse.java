package com.simples.dental.professionals.infrastructure.profissional.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ContatoForProfissonalResponse(
        @JsonProperty("id") String id,
        @JsonProperty("nome") String nome,
        @JsonProperty("contato") String contato
) {}
