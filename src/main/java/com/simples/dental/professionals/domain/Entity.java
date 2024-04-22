package com.simples.dental.professionals.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@EqualsAndHashCode
public abstract class Entity<ID extends Identifier> {
    private String value;

    protected ID id;

    protected Entity(final ID id) {
        Objects.requireNonNull(id, "'id' should not be null");
        this.id = id;
    }

    public abstract void validate();
}
