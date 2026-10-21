package com.example.demo.controller;

import java.time.LocalDateTime;

import com.example.demo.entity.Barbeiro;

public record BarbeiroResponse(
        Long id,
        String nome,
        String foto,
        int tentativasLoginFalhas,
        LocalDateTime bloqueadoAte) {

    public static BarbeiroResponse from(Barbeiro barbeiro) {
        return new BarbeiroResponse(
                barbeiro.getId(),
                barbeiro.getNome(),
                barbeiro.getFoto(),
                barbeiro.getTentativasLoginFalhas(),
                barbeiro.getBloqueadoAte());
    }
}
