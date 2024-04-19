package com.simples.dental.professionals.domain.profissional;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public enum CargoProfissional {
    DESENVOLVEDOR,
    DESIGNER,
    SUPORTE,
    TESTER;

    public static Optional<CargoProfissional> findByRole(String roleName) {
        return Arrays.stream(values())
                .filter(cargo -> cargo.name().equalsIgnoreCase(roleName))
                .findFirst();
    }

    public static String listNames() {
        return Arrays.stream(values()).map(Enum::name).collect(Collectors.joining(", ")).toLowerCase();
    }
}
