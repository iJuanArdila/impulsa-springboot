package com.example.demo.dto;

import com.example.demo.entity.EstadoPublicacion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PublicacionRequestDTO(
        @NotNull(message = "La fechaPrevista es obligatoria") LocalDate fechaPrevista,
        @NotBlank(message = "El formato es obligatorio") String formato,
        @NotBlank(message = "El objetivo es obligatorio") String objetivo,
        @NotNull(message = "El estado es obligatorio (BORRADOR, PROGRAMADA o PUBLICADA)") EstadoPublicacion estado,
        @NotNull(message = "El negocioId es obligatorio") Long negocioId
) {
}
