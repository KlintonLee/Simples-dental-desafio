package com.simples.dental.professionals.infrastructure.contato.persistence;

import com.simples.dental.professionals.domain.contato.Contato;
import com.simples.dental.professionals.domain.contato.ContatoId;
import com.simples.dental.professionals.infrastructure.profissional.persistence.ProfissionalJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "Contato")
@Table(name = "contatos")
public class ContatoJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "contato", nullable = false)
    private String contato;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profissional_id", referencedColumnName = "id", nullable = false)
    private ProfissionalJpaEntity profissional;

    @Column(name = "created_date", nullable = false, columnDefinition = "DATE")
    private LocalDate createdDate;

    public ContatoJpaEntity() {}

    private ContatoJpaEntity(
            final String id,
            final String nome,
            final String contato,
            final ProfissionalJpaEntity profissional,
            final LocalDate createdDate
    ) {
        this.id = id;
        this.nome = nome;
        this.contato = contato;
        this.profissional = profissional;
        this.createdDate = createdDate;
    }

    public static ContatoJpaEntity from(Contato contato) {
        return new ContatoJpaEntity(
                contato.getId().getValue(),
                contato.getNome(),
                contato.getContato(),
                ProfissionalJpaEntity.from(contato.getProfissional()),
                contato.getCreatedDate()
        );
    }

    public Contato toAggregate() {
        return Contato.with(
                ContatoId.from(id),
                nome,
                contato,
                profissional.toAggregate(),
                createdDate
        );
    }
}
