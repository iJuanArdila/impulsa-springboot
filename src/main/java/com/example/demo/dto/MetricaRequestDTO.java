package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record MetricaRequestDTO(
        @NotNull(message = "El alcance es obligatorio") @PositiveOrZero(message = "El alcance no puede ser negativo") Integer alcance,
        @NotNull(message = "Las interacciones son obligatorias") @PositiveOrZero(message = "Las interacciones no pueden ser negativas") Integer interacciones,
        @NotNull(message = "Los mensajesRecibidos son obligatorios") @PositiveOrZero(message = "Los mensajesRecibidos no pueden ser negativos") Integer mensajesRecibidos,
        @NotNull(message = "Las ventas son obligatorias") @PositiveOrZero(message = "Las ventas no pueden ser negativas") Integer ventas,
        @NotNull(message = "El publicacionId es obligatorio") Long publicacionId
) {
}
