package br.edu.ifma.imobiliaria.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "imoveis")
@Getter
@Setter
@NoArgsConstructor
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tipoImovel;
    private String endereco;
    private String cep;
    private Integer dormitorios;
    private Integer banheiros;
    private Integer suites;
    private Integer metragem;

    @Column(name = "valor_aluguel_sug")
    private BigDecimal valorAluguelSugerido;

    private String obs;
}