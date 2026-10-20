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
    public List<Barbeiro> listar() {
        return barbeiroService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barbeiro> buscarPorId(@PathVariable Long id) {
        return barbeiroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Barbeiro> cadastrar(@RequestBody Barbeiro dados) {
        if (dados == null || !nomeValido(dados.getNome())) {
            return ResponseEntity.badRequest().build();
        }

        Barbeiro criado = barbeiroService.cadastrar(dados.getNome(), dados.getFoto());
        return ResponseEntity.created(URI.create("/api/barbeiros/" + criado.getId())).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barbeiro> atualizar(@PathVariable Long id, @RequestBody Barbeiro dados) {
        if (dados == null || !nomeValido(dados.getNome())) {
            return ResponseEntity.badRequest().build();
        }

        return barbeiroService.atualizar(id, dados.getNome(), dados.getFoto())
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

    private boolean nomeValido(String nome) {
        return nome != null && !nome.isBlank();
    }
}
