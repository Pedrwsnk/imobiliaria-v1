package br.edu.ifma.imobiliaria.service;

import br.edu.ifma.imobiliaria.dto.LocacaoRequest;
import br.edu.ifma.imobiliaria.exception.*;
import br.edu.ifma.imobiliaria.model.Locacao;
import br.edu.ifma.imobiliaria.repository.LocacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocacaoService {

    private final LocacaoRepository repository;
    private final ImovelService imovelService;
    private final ClienteService clienteService;

    public List<Locacao> listar() {
        return repository.findAll();
    }

    public Locacao buscar(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Locação", id));
    }

    @Transactional
    public Locacao criar(LocacaoRequest req) {
        Locacao locacao = new Locacao();
        preencher(locacao, req);
        return repository.save(locacao);
    }

    @Transactional
    public Locacao atualizar(Integer id, LocacaoRequest req) {
        Locacao locacao = buscar(id);
        preencher(locacao, req);
        return repository.save(locacao);
    }

    @Transactional
    public void excluir(Integer id) {
        buscar(id);
        repository.deleteById(id);
    }

    private void preencher(Locacao l, LocacaoRequest r) {
        if (r.dataFim() != null && r.dataFim().isBefore(r.dataInicio())) {
            throw new RegraNegocioException("A data de fim não pode ser anterior à data de início");
        }
        l.setImovel(imovelService.buscar(r.idImovel()));
        l.setInquilino(clienteService.buscar(r.idInquilino()));
        l.setAtivo(r.ativo() != null ? r.ativo() : true);
        l.setDataInicio(r.dataInicio());
        l.setDataFim(r.dataFim());
        l.setDiaVencimento(r.diaVencimento());
        l.setPercMulta(r.percMulta());
        l.setValorAluguel(r.valorAluguel());
        l.setObs(r.obs());
    }
}