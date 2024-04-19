package com.simples.dental.professionals.domain.profissional;

import com.simples.dental.professionals.domain.Identifier;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@EqualsAndHashCode(callSuper = false)
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
