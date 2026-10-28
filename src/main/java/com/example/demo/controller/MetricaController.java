package com.example.demo.controller;

import com.example.demo.dto.MetricaRequestDTO;
import com.example.demo.dto.MetricaResponseDTO;
import com.example.demo.service.MetricaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/metricas")
@RequiredArgsConstructor
public class MetricaController {

    private final MetricaService metricaService;

    @GetMapping
    public ResponseEntity<List<MetricaResponseDTO>> listar() {
        return ResponseEntity.ok(metricaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetricaResponseDTO> obtener(@PathVariable Long id) {
        return metricaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MetricaResponseDTO> crear(@Valid @RequestBody MetricaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(metricaService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetricaResponseDTO> actualizar(@PathVariable Long id,
                                                         @Valid @RequestBody MetricaRequestDTO dto) {
        return metricaService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return metricaService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
