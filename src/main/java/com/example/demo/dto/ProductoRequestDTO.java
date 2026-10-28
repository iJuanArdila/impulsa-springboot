package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductoRequestDTO(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        String descripcion,
        @NotNull(message = "El negocioId es obligatorio") Long negocioId
) {
}
