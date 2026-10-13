package br.edu.ifma.imobiliaria.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public record LocacaoRequest(
        @NotNull(message = "O imóvel é obrigatório") Integer idImovel,
        @NotNull(message = "O inquilino é obrigatório") Integer idInquilino,
        Boolean ativo,
        @NotNull(message = "A data de início é obrigatória") LocalDate dataInicio,
        LocalDate dataFim,

        @NotNull(message = "O dia de vencimento é obrigatório") @Min(value = 1, message = "O dia de vencimento deve ser entre 1 e 31") @Max(value = 31, message = "O dia de vencimento deve ser entre 1 e 31") Integer diaVencimento,

        @DecimalMin(value = "0.0") @DecimalMax(value = "100.0") BigDecimal percMulta,

        @NotNull(message = "O valor do aluguel é obrigatório") @Positive(message = "O valor do aluguel deve ser maior que zero") BigDecimal valorAluguel,

        String obs) {
}