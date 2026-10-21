package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "barbeiros")
@Getter
@Setter
@NoArgsConstructor
public class Barbeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String foto;

    @Column(nullable = false)
    private String senhaHash;

    @Column(nullable = false)
    private int tentativasLoginFalhas;

    private LocalDateTime bloqueadoAte;

    public Barbeiro(String nome, String foto) {
        this.nome = nome;
        this.foto = foto;
    }
}
