package com.example.demo.service;

import com.example.demo.dto.ProductoRequestDTO;
import com.example.demo.dto.ProductoResponseDTO;
import com.example.demo.entity.Producto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.NegocioRepository;
import com.example.demo.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final NegocioRepository negocioRepository;

    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> listar() {
        return productoRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public Optional<ProductoResponseDTO> buscarPorId(Long id) {
        return productoRepository.findById(id).map(this::toResponse);
    }

    @Transactional
    public ProductoResponseDTO crear(ProductoRequestDTO dto) {
        Producto producto = new Producto();
        aplicar(producto, dto);
        return toResponse(productoRepository.save(producto));
    }

    @Transactional
    public Optional<ProductoResponseDTO> actualizar(Long id, ProductoRequestDTO dto) {
        return productoRepository.findById(id).map(producto -> {
            aplicar(producto, dto);
            return toResponse(productoRepository.save(producto));
        });
    }

    @Transactional
    public boolean eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            return false;
        }
        productoRepository.deleteById(id);
        return true;
    }

    private void aplicar(Producto producto, ProductoRequestDTO dto) {
        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setNegocio(negocioRepository.findById(dto.negocioId())
                .orElseThrow(() -> new ResourceNotFoundException("Negocio", dto.negocioId())));
    }

    private ProductoResponseDTO toResponse(Producto p) {
        return new ProductoResponseDTO(p.getId(), p.getNombre(), p.getDescripcion(), p.getNegocio().getId());
    }
}
