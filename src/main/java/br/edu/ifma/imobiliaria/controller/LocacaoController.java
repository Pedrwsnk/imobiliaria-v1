package br.edu.ifma.imobiliaria.controller;

import br.edu.ifma.imobiliaria.dto.LocacaoRequest;
import br.edu.ifma.imobiliaria.model.Locacao;
import br.edu.ifma.imobiliaria.service.LocacaoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locacoes")
public class LocacaoController {

    private final LocacaoService locacaoService;

    public LocacaoController(LocacaoService locacaoService) {
        this.locacaoService = locacaoService;
    }

    @GetMapping
    public ResponseEntity<List<Locacao>> listarTodas() {
        List<Locacao> locacoes = locacaoService.listar();
        return ResponseEntity.ok(locacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Locacao> buscarPorId(@PathVariable Integer id) {
        Locacao locacao = locacaoService.buscar(id);
        return ResponseEntity.ok(locacao);
    }

    @PostMapping
    public ResponseEntity<Locacao> criar(@RequestBody @Valid LocacaoRequest request) {
        Locacao novaLocacao = locacaoService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaLocacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locacao> atualizar(@PathVariable Integer id, @RequestBody @Valid LocacaoRequest request) {
        Locacao locacaoAtualizada = locacaoService.atualizar(id, request);
        return ResponseEntity.ok(locacaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        locacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}