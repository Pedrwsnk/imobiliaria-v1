package br.edu.ifma.imobiliaria.controller;

import br.edu.ifma.imobiliaria.dto.ImovelRequest;
import br.edu.ifma.imobiliaria.model.Imovel;
import br.edu.ifma.imobiliaria.service.ImovelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/imoveis")
@RequiredArgsConstructor
public class ImovelController {

    private final ImovelService service;

    @GetMapping
    public List<Imovel> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Imovel buscar(@PathVariable Integer id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<Imovel> criar(@RequestBody @Valid ImovelRequest req) {
        Imovel criado = service.criar(req);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(criado.getId()).toUri();
        return ResponseEntity.created(uri).body(criado);
    }

    @PutMapping("/{id}")
    public Imovel atualizar(@PathVariable Integer id, @RequestBody @Valid ImovelRequest req) {
        return service.atualizar(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) {
        service.excluir(id);
    }
}