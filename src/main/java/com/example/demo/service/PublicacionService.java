package com.example.demo.service;

import com.example.demo.dto.PublicacionRequestDTO;
import com.example.demo.dto.PublicacionResponseDTO;
import com.example.demo.entity.Publicacion;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.NegocioRepository;
import com.example.demo.repository.PublicacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final NegocioRepository negocioRepository;

    @Transactional(readOnly = true)
    public List<PublicacionResponseDTO> listar() {
        return publicacionRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public Optional<PublicacionResponseDTO> buscarPorId(Long id) {
        return publicacionRepository.findById(id).map(this::toResponse);
    }

    @Transactional
    public PublicacionResponseDTO crear(PublicacionRequestDTO dto) {
        Publicacion publicacion = new Publicacion();
        aplicar(publicacion, dto);
        return toResponse(publicacionRepository.save(publicacion));
    }

    @Transactional
    public Optional<PublicacionResponseDTO> actualizar(Long id, PublicacionRequestDTO dto) {
        return publicacionRepository.findById(id).map(publicacion -> {
            aplicar(publicacion, dto);
            return toResponse(publicacionRepository.save(publicacion));
        });
    }

    @Transactional
    public boolean eliminar(Long id) {
        if (!publicacionRepository.existsById(id)) {
            return false;
        }
        publicacionRepository.deleteById(id);
        publicacionRepository.flush();
        return true;
    }

    private void aplicar(Publicacion publicacion, PublicacionRequestDTO dto) {
        publicacion.setFechaPrevista(dto.fechaPrevista());
        publicacion.setFormato(dto.formato());
        publicacion.setObjetivo(dto.objetivo());
        publicacion.setEstado(dto.estado());
        publicacion.setNegocio(negocioRepository.findById(dto.negocioId())
                .orElseThrow(() -> new ResourceNotFoundException("Negocio", dto.negocioId())));
    }

    private PublicacionResponseDTO toResponse(Publicacion p) {
        return new PublicacionResponseDTO(p.getId(), p.getFechaPrevista(), p.getFormato(),
                p.getObjetivo(), p.getEstado(), p.getNegocio().getId());
    }
}
