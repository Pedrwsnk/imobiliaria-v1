package br.edu.ifma.imobiliaria.repository;

import br.edu.ifma.imobiliaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByCpf(String cpf);

    boolean existsByCpfAndIdNot(String cpf, Integer id);
}