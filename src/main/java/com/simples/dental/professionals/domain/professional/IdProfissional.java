package com.simples.dental.professionals.domain.professional;

import com.simples.dental.professionals.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class IdProfissional extends Identifier {

    private String value;

    private IdProfissional(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static IdProfissional unique() {
        return from(UUID.randomUUID());
    }

    public static IdProfissional from(UUID id) {
        return new IdProfissional(id.toString());
    }

    public static IdProfissional from(String id) {
        return new IdProfissional(id);
    }
}