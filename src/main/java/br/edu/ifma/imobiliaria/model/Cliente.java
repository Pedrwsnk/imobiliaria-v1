package br.edu.ifma.imobiliaria.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome_cliente")
    private String nome;

    private String cpf;
    private String telefone;
    private String email;

    @Column(name = "dt_nascimento")
    private LocalDate dataNascimento;
}