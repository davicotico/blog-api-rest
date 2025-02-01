package com.davidticona.apiblog.app.controller;

import com.davidticona.apiblog.app.data.dto.BlogCreateDto;
import com.davidticona.apiblog.app.data.dto.BlogUpdateDto;
import com.davidticona.apiblog.app.data.dto.ComentarioCreateDto;
import com.davidticona.apiblog.app.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("blog")
public class BlogController {
    private final BlogService service;

    public BlogController(BlogService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody BlogCreateDto dto) {
        Integer id = service.crear(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("{id}/comentario")
    public ResponseEntity<?> agregarComentario(
            @PathVariable("id") Integer idBlog,
            @Valid @RequestBody ComentarioCreateDto dto) {
        Integer idComentario = service.agregarComentario(idBlog, dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(idComentario)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable("id") Integer idBlog,
            @Valid @RequestBody BlogUpdateDto dto) {
        service.actualizar(idBlog, dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> obtenerBlogPorId(@PathVariable("id") Integer idBlog) {
        return ResponseEntity.ok(service.consultar(idBlog));
    }

    @GetMapping("{idBlog}/comentarios")
    public ResponseEntity<?> obtenerComentariosDeBlog(@PathVariable("idBlog") Integer idBlog) {
        return ResponseEntity.ok(service.consultarComentariosDeBlog(idBlog));
    }

    @GetMapping("{idBlog}/historial")
    public ResponseEntity<?> obtenerHistorialDeBlog(@PathVariable("idBlog") Integer idBlog) {
        return ResponseEntity.ok(service.consultarHistorialDeBlog(idBlog));
    }
}
