package com.davidticona.apiblog.app.repository;

import com.davidticona.apiblog.app.data.entity.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialRepository extends JpaRepository<Historial, Integer> {
    List<Historial> findByBlogIdOrderByIdDesc(Integer blogId);
}
