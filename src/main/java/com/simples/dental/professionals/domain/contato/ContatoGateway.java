package com.simples.dental.professionals.domain.contato;

import java.util.Optional;

public interface ContatoGateway {

    Contato create(Contato contato);

    Optional<Contato> findById(ContatoId id);

    Contato update(Contato contato);

    void deleteById(ContatoId id);
}