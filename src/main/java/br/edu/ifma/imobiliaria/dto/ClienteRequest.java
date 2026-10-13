package br.edu.ifma.imobiliaria.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record ClienteRequest(
        @NotBlank(message = "O nome é obrigatório") @Size(max = 255, message = "O nome deve ter no máximo 255 caracteres") String nome,

        @NotBlank(message = "O CPF é obrigatório") @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos numéricos") String cpf,

        @Size(max = 12, message = "O telefone deve ter no máximo 12 caracteres") String telefone,

        @Email(message = "E-mail inválido") @Size(max = 100) String email,

        @Past(message = "A data de nascimento deve estar no passado") LocalDate dataNascimento) {
}