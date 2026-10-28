package com.example.demo.repository;

import com.example.demo.entity.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
}
