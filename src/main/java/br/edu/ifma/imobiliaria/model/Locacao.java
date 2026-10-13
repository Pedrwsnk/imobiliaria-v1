package br.edu.ifma.imobiliaria.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "locacao")
@Getter
@Setter
@NoArgsConstructor
public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_imovel")
    private Imovel imovel;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_inquilino")
    private Cliente inquilino;

    private Boolean ativo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Integer diaVencimento;
    private BigDecimal percMulta;
    private BigDecimal valorAluguel;
    private String obs;
}