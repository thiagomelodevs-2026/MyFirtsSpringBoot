package com.example.demo.service;

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

    public Barbeiro cadastrar(String nome, String foto) {
        return barbeiroRepository.save(new Barbeiro(nome, foto));
    }

    public Optional<Barbeiro> atualizar(Long id, String nome, String foto) {
        return barbeiroRepository.findById(id)
                .map(barbeiro -> {
                    barbeiro.setNome(nome);
                    barbeiro.setFoto(foto);
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
