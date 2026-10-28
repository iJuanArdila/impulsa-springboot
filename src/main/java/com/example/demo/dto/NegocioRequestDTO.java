package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record NegocioRequestDTO(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "La categoría es obligatoria") String categoria,
        String descripcion,
        String datosContacto
) {
}
