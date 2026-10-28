package com.example.demo.dto;

public record MetricaResponseDTO(
        Long id,
        Integer alcance,
        Integer interacciones,
        Integer mensajesRecibidos,
        Integer ventas,
        Long publicacionId
) {
}
