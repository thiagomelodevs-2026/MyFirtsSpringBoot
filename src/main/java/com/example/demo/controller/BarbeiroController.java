package com.example.demo.controller;

import com.example.demo.entity.Barbeiro;
import com.example.demo.service.BarbeiroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/barbeiros")
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

    public BarbeiroController(BarbeiroService barbeiroService) {
        this.barbeiroService = barbeiroService;
    }

    @GetMapping
    public List<BarbeiroResponse> listar() {
        return barbeiroService.listar().stream().map(BarbeiroResponse::from).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarbeiroResponse> buscarPorId(@PathVariable Long id) {
        return barbeiroService.buscarPorId(id)
                .map(BarbeiroResponse::from)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<BarbeiroResponse> cadastrar(@RequestBody BarbeiroRequest dados) {
        if (!dadosValidos(dados)) {
            return ResponseEntity.badRequest().build();
        }

        Barbeiro criado = barbeiroService.cadastrar(dados);
        return ResponseEntity.created(URI.create("/api/barbeiros/" + criado.getId()))
                .body(BarbeiroResponse.from(criado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarbeiroResponse> atualizar(@PathVariable Long id, @RequestBody BarbeiroRequest dados) {
        if (!dadosValidos(dados)) {
            return ResponseEntity.badRequest().build();
        }

        return barbeiroService.atualizar(id, dados)
                .map(BarbeiroResponse::from)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!barbeiroService.excluir(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    private boolean dadosValidos(BarbeiroRequest dados) {
        return dados != null
                && dados.nome() != null
                && !dados.nome().isBlank()
                && dados.senhaHash() != null
                && !dados.senhaHash().isBlank()
                && dados.tentativasLoginFalhas() >= 0;
    }
}
