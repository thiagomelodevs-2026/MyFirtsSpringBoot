package com.example.demo.controller;

import java.time.LocalDateTime;

public record BarbeiroRequest(
        String nome,
        String foto,
        String senhaHash,
        int tentativasLoginFalhas,
        LocalDateTime bloqueadoAte) {
}
