package br.edu.ifma.imobiliaria.service;

import br.edu.ifma.imobiliaria.dto.ClienteRequest;
import br.edu.ifma.imobiliaria.exception.*;
import br.edu.ifma.imobiliaria.model.Cliente;
import br.edu.ifma.imobiliaria.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente buscar(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente", id));
    }

    @Transactional
    public Cliente criar(ClienteRequest req) {
        if (repository.existsByCpf(req.cpf())) {
            throw new RegraNegocioException("Já existe um cliente com este CPF");
        }
        Cliente cliente = new Cliente();
        preencher(cliente, req);
        return repository.save(cliente);
    }

    @Transactional
    public Cliente atualizar(Integer id, ClienteRequest req) {
        Cliente cliente = buscar(id);
        if (repository.existsByCpfAndIdNot(req.cpf(), id)) {
            throw new RegraNegocioException("Já existe outro cliente com este CPF");
        }
        preencher(cliente, req);
        return repository.save(cliente);
    }

    @Transactional
    public void excluir(Integer id) {
        buscar(id); // garante o 404 se não existir
        repository.deleteById(id);
    }

    private void preencher(Cliente c, ClienteRequest r) {
        c.setNome(r.nome());
        c.setCpf(r.cpf());
        c.setTelefone(r.telefone());
        c.setEmail(r.email());
        c.setDataNascimento(r.dataNascimento());
    }
}