package com.example.demo.repository;

import com.example.demo.entity.Negocio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NegocioRepository extends JpaRepository<Negocio, Long> {
}
