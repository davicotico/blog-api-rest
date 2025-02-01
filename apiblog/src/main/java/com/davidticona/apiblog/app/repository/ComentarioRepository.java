package com.davidticona.apiblog.app.repository;

import com.davidticona.apiblog.app.data.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
}
