package com.example.demo.service;

import com.example.demo.controller.BarbeiroRequest;
import com.example.demo.entity.Barbeiro;
import com.example.demo.repository.BarbeiroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;

    public BarbeiroService(BarbeiroRepository barbeiroRepository) {
        this.barbeiroRepository = barbeiroRepository;
    }

    public List<Barbeiro> listar() {
        return barbeiroRepository.findAll();
    }

    public Optional<Barbeiro> buscarPorId(Long id) {
        return barbeiroRepository.findById(id);
    }

    public Barbeiro cadastrar(BarbeiroRequest dados) {
        Barbeiro barbeiro = new Barbeiro(dados.nome(), dados.foto());
        barbeiro.setSenhaHash(dados.senhaHash());
        barbeiro.setTentativasLoginFalhas(dados.tentativasLoginFalhas());
        barbeiro.setBloqueadoAte(dados.bloqueadoAte());
        return barbeiroRepository.save(barbeiro);
    }

    public Optional<Barbeiro> atualizar(Long id, BarbeiroRequest dados) {
        return barbeiroRepository.findById(id)
                .map(barbeiro -> {
                    barbeiro.setNome(dados.nome());
                    barbeiro.setFoto(dados.foto());
                    barbeiro.setSenhaHash(dados.senhaHash());
                    barbeiro.setTentativasLoginFalhas(dados.tentativasLoginFalhas());
                    barbeiro.setBloqueadoAte(dados.bloqueadoAte());
                    return barbeiroRepository.save(barbeiro);
                });
    }

    public boolean excluir(Long id) {
        if (!barbeiroRepository.existsById(id)) {
            return false;
        }

        barbeiroRepository.deleteById(id);
        return true;
    }
}
