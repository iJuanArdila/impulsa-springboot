package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "metrica")
@Getter
@Setter
@NoArgsConstructor
public class Metrica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer alcance;

    @Column(nullable = false)
    private Integer interacciones;

    @Column(nullable = false)
    private Integer mensajesRecibidos;

    @Column(nullable = false)
    private Integer ventas;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;
}
