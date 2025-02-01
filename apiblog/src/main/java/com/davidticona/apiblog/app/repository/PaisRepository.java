package com.davidticona.apiblog.app.repository;

import com.davidticona.apiblog.app.data.entity.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {
    Optional<Pais> findByCodigo(String codigo);
}
