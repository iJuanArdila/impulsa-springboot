package com.example.demo.controller;

import com.example.demo.dto.PublicacionRequestDTO;
import com.example.demo.dto.PublicacionResponseDTO;
import com.example.demo.service.PublicacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publicaciones")
@RequiredArgsConstructor
public class PublicacionController {

    private final PublicacionService publicacionService;

    @GetMapping
    public ResponseEntity<List<PublicacionResponseDTO>> listar() {
        return ResponseEntity.ok(publicacionService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublicacionResponseDTO> obtener(@PathVariable Long id) {
        return publicacionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PublicacionResponseDTO> crear(@Valid @RequestBody PublicacionRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(publicacionService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PublicacionResponseDTO> actualizar(@PathVariable Long id,
                                                             @Valid @RequestBody PublicacionRequestDTO dto) {
        return publicacionService.actualizar(id, dto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return publicacionService.eliminar(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
