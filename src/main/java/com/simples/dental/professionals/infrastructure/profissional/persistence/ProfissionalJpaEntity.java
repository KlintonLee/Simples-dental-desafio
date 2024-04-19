package com.simples.dental.professionals.infrastructure.profissional.persistence;

import com.simples.dental.professionals.domain.profissional.CargoProfissional;
import com.simples.dental.professionals.domain.profissional.Profissional;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "Profissional")
@Table(name = "profissionais")
@Getter
@Setter
public class ProfissionalJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "cargo", nullable = false)
    private CargoProfissional cargo;

    @Column(name = "nascimento", nullable = false, columnDefinition = "DATE")
    private LocalDate nascimento;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "created_date", nullable = false, columnDefinition = "DATE")
    private LocalDate createdDate;

    public ProfissionalJpaEntity() {}

    private ProfissionalJpaEntity(
            final String id,
            final String nome,
            final CargoProfissional cargo,
            final LocalDate nascimento,
            final boolean active,
            final LocalDate createdDate
    ) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.nascimento = nascimento;
        this.active = active;
        this.createdDate = createdDate;
    }

    public static ProfissionalJpaEntity from(Profissional profissional) {
        return new ProfissionalJpaEntity(
                profissional.getId().getValue(),
                profissional.getNome(),
                profissional.getCargo(),
                profissional.getNascimento(),
                profissional.isActive(),
                profissional.getCreatedDate()
        );
    }
}
