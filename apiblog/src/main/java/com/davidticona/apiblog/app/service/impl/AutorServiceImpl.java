package com.davidticona.apiblog.app.service.impl;

import com.davidticona.apiblog.app.data.dto.AutorCreateDto;
import com.davidticona.apiblog.app.data.entity.Autor;
import com.davidticona.apiblog.app.data.entity.Pais;
import com.davidticona.apiblog.app.repository.AutorRepository;
import com.davidticona.apiblog.app.repository.PaisRepository;
import com.davidticona.apiblog.app.service.AutorService;
import com.davidticona.apiblog.exception.NoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutorServiceImpl implements AutorService {

    private final AutorRepository repository;
    private final PaisRepository paisRepository;

    public AutorServiceImpl(AutorRepository repository, PaisRepository paisRepository) {
        this.repository = repository;
        this.paisRepository = paisRepository;
    }

    @Override
    public Integer crear(AutorCreateDto dto) {
         Pais pais = paisRepository.findByCodigo(dto.codigoPais())
                 .orElseThrow(() -> new NoEncontradoException("Código de país desconocido"));
         Autor autor = new Autor();
         autor.setPais(pais);
         autor.setNombres(dto.nombres());
         autor.setPaterno(dto.paterno());
         autor.setMaterno(dto.materno());
         autor.setNacimiento(dto.nacimiento());
         autor.setCorreoElectronico(dto.correoElectronico());
         repository.save(autor);
         return autor.getId();
    }
}
