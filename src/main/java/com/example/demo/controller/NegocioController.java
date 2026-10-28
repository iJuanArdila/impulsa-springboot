package com.example.demo.controller;

import com.example.demo.dto.NegocioRequestDTO;
import com.example.demo.dto.NegocioResponseDTO;
import com.example.demo.service.NegocioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/negocios")
@RequiredArgsConstructor
public class NegocioController {

    private final NegocioService negocioService;

    @GetMapping
    public ResponseEntity<List<NegocioResponseDTO>> listar() {
        return ResponseEntity.ok(negocioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NegocioResponseDTO> obtener(@PathVariable Long id) {
        return negocioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<NegocioResponseDTO> crear(@Valid @RequestBody NegocioRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(negocioService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NegocioResponseDTO> actualizar(@PathVariable Long id,
                                                         @Valid @RequestBody NegocioRequestDTO dto) {
        return negocioService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return negocioService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
