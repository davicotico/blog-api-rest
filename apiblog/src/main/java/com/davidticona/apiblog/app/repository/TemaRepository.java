package com.davidticona.apiblog.app.repository;

import com.davidticona.apiblog.app.data.entity.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Integer> {
}
