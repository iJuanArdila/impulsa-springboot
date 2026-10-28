package com.example.demo.service;

import com.example.demo.dto.NegocioRequestDTO;
import com.example.demo.dto.NegocioResponseDTO;
import com.example.demo.entity.Negocio;
import com.example.demo.repository.NegocioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NegocioService {

    private final NegocioRepository negocioRepository;

    @Transactional(readOnly = true)
    public List<NegocioResponseDTO> listar() {
        return negocioRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public Optional<NegocioResponseDTO> buscarPorId(Long id) {
        return negocioRepository.findById(id).map(this::toResponse);
    }

    @Transactional
    public NegocioResponseDTO crear(NegocioRequestDTO dto) {
        Negocio negocio = new Negocio();
        aplicar(negocio, dto);
        return toResponse(negocioRepository.save(negocio));
    }

    @Transactional
    public Optional<NegocioResponseDTO> actualizar(Long id, NegocioRequestDTO dto) {
        return negocioRepository.findById(id).map(negocio -> {
            aplicar(negocio, dto);
            return toResponse(negocioRepository.save(negocio));
        });
    }

    @Transactional
    public boolean eliminar(Long id) {
        if (!negocioRepository.existsById(id)) {
            return false;
        }
        negocioRepository.deleteById(id);
        negocioRepository.flush();
        return true;
    }

    private void aplicar(Negocio negocio, NegocioRequestDTO dto) {
        negocio.setNombre(dto.nombre());
        negocio.setCategoria(dto.categoria());
        negocio.setDescripcion(dto.descripcion());
        negocio.setDatosContacto(dto.datosContacto());
    }

    private NegocioResponseDTO toResponse(Negocio n) {
        return new NegocioResponseDTO(n.getId(), n.getNombre(), n.getCategoria(),
                n.getDescripcion(), n.getDatosContacto());
    }
}
