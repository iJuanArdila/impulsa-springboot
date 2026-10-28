package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "publicacion")
@Getter
@Setter
@NoArgsConstructor
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_prevista", nullable = false)
    private LocalDate fechaPrevista;

    @Column(nullable = false)
    private String formato;

    @Column(nullable = false)
    private String objetivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPublicacion estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "negocio_id", nullable = false)
    private Negocio negocio;
}
