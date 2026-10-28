package com.example.demo.dto;

public record ProductoResponseDTO(
        Long id,
        String nombre,
        String descripcion,
        Long negocioId
) {
}
