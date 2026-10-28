package com.example.demo.service;

import com.example.demo.dto.MetricaRequestDTO;
import com.example.demo.dto.MetricaResponseDTO;
import com.example.demo.entity.Metrica;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.MetricaRepository;
import com.example.demo.repository.PublicacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetricaService {

    private final MetricaRepository metricaRepository;
    private final PublicacionRepository publicacionRepository;

    @Transactional(readOnly = true)
    public List<MetricaResponseDTO> listar() {
        return metricaRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public Optional<MetricaResponseDTO> buscarPorId(Long id) {
        return metricaRepository.findById(id).map(this::toResponse);
    }

    @Transactional
    public MetricaResponseDTO crear(MetricaRequestDTO dto) {
        Metrica metrica = new Metrica();
        aplicar(metrica, dto);
        return toResponse(metricaRepository.save(metrica));
    }

    @Transactional
    public Optional<MetricaResponseDTO> actualizar(Long id, MetricaRequestDTO dto) {
        return metricaRepository.findById(id).map(metrica -> {
            aplicar(metrica, dto);
            return toResponse(metricaRepository.save(metrica));
        });
    }

    @Transactional
    public boolean eliminar(Long id) {
        if (!metricaRepository.existsById(id)) {
            return false;
        }
        metricaRepository.deleteById(id);
        return true;
    }

    private void aplicar(Metrica metrica, MetricaRequestDTO dto) {
        metrica.setAlcance(dto.alcance());
        metrica.setInteracciones(dto.interacciones());
        metrica.setMensajesRecibidos(dto.mensajesRecibidos());
        metrica.setVentas(dto.ventas());
        metrica.setPublicacion(publicacionRepository.findById(dto.publicacionId())
                .orElseThrow(() -> new ResourceNotFoundException("Publicacion", dto.publicacionId())));
    }

    private MetricaResponseDTO toResponse(Metrica m) {
        return new MetricaResponseDTO(m.getId(), m.getAlcance(), m.getInteracciones(),
                m.getMensajesRecibidos(), m.getVentas(), m.getPublicacion().getId());
    }
}
