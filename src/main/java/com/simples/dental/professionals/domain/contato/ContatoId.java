package com.simples.dental.professionals.domain.contato;

import com.simples.dental.professionals.domain.Identifier;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
@EqualsAndHashCode(callSuper = false)
public class ContatoId extends Identifier {

    private String value;

    private ContatoId(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static ContatoId unique() {
        return from(UUID.randomUUID());
    }

    public static ContatoId from(UUID id) {
        return new ContatoId(id.toString());
    }

    public static ContatoId from(String id) {
        return new ContatoId(id);
    }
}
