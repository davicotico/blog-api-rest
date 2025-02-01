package com.davidticona.apiblog.app.controller;

import com.davidticona.apiblog.app.data.dto.AutorCreateDto;
import com.davidticona.apiblog.app.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("autor")
public class AutorController {
    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crearAutor(@Valid @RequestBody AutorCreateDto dto) {
        System.out.println(dto);
        Integer idAAutor = service.crear(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(idAAutor)
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
