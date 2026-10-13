package br.edu.ifma.imobiliaria.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ImovelRequest(
        @NotBlank(message = "O tipo do imóvel é obrigatório") @Size(max = 50) String tipoImovel,

        @NotBlank(message = "O endereço é obrigatório") @Size(max = 255) String endereco,

        @Size(max = 10) String cep,
        @PositiveOrZero Integer dormitorios,
        @PositiveOrZero Integer banheiros,
        @PositiveOrZero Integer suites,
        @Positive(message = "A metragem deve ser maior que zero") Integer metragem,
        @Positive(message = "O valor sugerido deve ser maior que zero") BigDecimal valorAluguelSugerido,
        String obs) {
}