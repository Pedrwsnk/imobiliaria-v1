package br.edu.ifma.imobiliaria.service;

import br.edu.ifma.imobiliaria.dto.ImovelRequest;
import br.edu.ifma.imobiliaria.exception.RecursoNaoEncontradoException;
import br.edu.ifma.imobiliaria.model.Imovel;
import br.edu.ifma.imobiliaria.repository.ImovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImovelService {

    private final ImovelRepository repository;

    public List<Imovel> listar() {
        return repository.findAll();
    }

    public Imovel buscar(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Imóvel", id));
    }

    @Transactional
    public Imovel criar(ImovelRequest req) {
        Imovel imovel = new Imovel();
        preencher(imovel, req);
        return repository.save(imovel);
    }

    @Transactional
    public Imovel atualizar(Integer id, ImovelRequest req) {
        Imovel imovel = buscar(id);
        preencher(imovel, req);
        return repository.save(imovel);
    }

    @Transactional
    public void excluir(Integer id) {
        buscar(id);
        repository.deleteById(id);
    }

    private void preencher(Imovel i, ImovelRequest r) {
        i.setTipoImovel(r.tipoImovel());
        i.setEndereco(r.endereco());
        i.setCep(r.cep());
        i.setDormitorios(r.dormitorios());
        i.setBanheiros(r.banheiros());
        i.setSuites(r.suites());
        i.setMetragem(r.metragem());
        i.setValorAluguelSugerido(r.valorAluguelSugerido());
        i.setObs(r.obs());
    }
}