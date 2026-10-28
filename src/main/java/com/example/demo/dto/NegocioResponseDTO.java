package com.example.demo.dto;

public record NegocioResponseDTO(
        Long id,
        String nombre,
        String categoria,
        String descripcion,
        String datosContacto
) {
}
