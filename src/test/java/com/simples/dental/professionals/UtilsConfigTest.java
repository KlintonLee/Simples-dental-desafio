package com.simples.dental.professionals;

import com.simples.dental.professionals.domain.profissional.CargoProfissional;

import java.time.LocalDate;

public class UtilsConfigTest {

    public static String EXPECTED_PROFISSIONAL_NOME = "John Doe";

    public static CargoProfissional EXPECTED_CARGO = CargoProfissional.DESENVOLVEDOR;

    public static LocalDate EXPECTED_NASCIMENTO = LocalDate.of(2000, 12, 25);

    public static String EXPECTED_CONTATO_NOME = "fixo";

    public static String EXPECTED_CONTATO = "14999999999";

    public static int EXPECTED_QUERY_PAGE = 1;

    public static int EXPECTED_QUERY_PER_PAGE = 10;
}
