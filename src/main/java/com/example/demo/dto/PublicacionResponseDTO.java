package com.example.demo.dto;

import com.example.demo.entity.EstadoPublicacion;

import java.time.LocalDate;

public record PublicacionResponseDTO(
        Long id,
        LocalDate fechaPrevista,
        String formato,
        String objetivo,
        EstadoPublicacion estado,
        Long negocioId
) {
}
