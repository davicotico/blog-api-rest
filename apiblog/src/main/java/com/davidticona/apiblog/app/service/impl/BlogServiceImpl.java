package com.davidticona.apiblog.app.service.impl;

import com.davidticona.apiblog.app.data.dto.*;
import com.davidticona.apiblog.app.data.entity.*;
import com.davidticona.apiblog.app.repository.AutorRepository;
import com.davidticona.apiblog.app.repository.BlogRepository;
import com.davidticona.apiblog.app.repository.PaisRepository;
import com.davidticona.apiblog.app.repository.TemaRepository;
import com.davidticona.apiblog.app.service.BlogService;
import com.davidticona.apiblog.app.service.HistorialService;
import com.davidticona.apiblog.exception.NoEncontradoException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.IntSummaryStatistics;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    private final BlogRepository repository;

    private final TemaRepository temaRepository;

    private final AutorRepository autorRepository;

    private final PaisRepository paisRepository;

    private final HistorialService historialService;

    public BlogServiceImpl(
            BlogRepository repository,
            TemaRepository temaRepository,
            AutorRepository autorRepository,
            PaisRepository paisRepository,
            HistorialService historialService) {
        this.repository = repository;
        this.temaRepository = temaRepository;
        this.autorRepository = autorRepository;
        this.paisRepository = paisRepository;
        this.historialService = historialService;
    }

    @Override
    public Integer crear(BlogCreateDto dto) {
        Autor autor = autorRepository.findById(dto.idAutor())
                .orElseThrow(() -> new NoEncontradoException("Autor no encontrado"));
        Tema tema = temaRepository.findById(dto.idTema())
                .orElseThrow(() -> new NoEncontradoException("Tema no encontrado"));
        Blog blog = new Blog();
        blog.setAutor(autor);
        blog.setTema(tema);
        blog.setTitulo(dto.titulo());
        blog.setContenido(dto.contenido());
        blog.setPeriodicidad(dto.periodicidad());
        blog.setPermiteComentarios(dto.permiteComentarios());
        repository.save(blog);
        return blog.getId();
    }

    @Transactional
    @Override
    public Integer agregarComentario(Integer idBlog, ComentarioCreateDto dto) {
        Blog blog = repository.findById(idBlog)
                .orElseThrow(() -> new NoEncontradoException("Blog no encontrado"));

        if (!blog.getPermiteComentarios()) {
            throw new IllegalStateException("Este blog no permite comentarios");
        }

        Pais pais = paisRepository.findByCodigo(dto.codigoPais())
                .orElseThrow(() -> new NoEncontradoException("País no encontrado"));
        Comentario comentario = new Comentario();
        comentario.setBlog(blog);
        comentario.setPais(pais);
        comentario.setNombreCompleto(dto.nombreCompleto());
        comentario.setCorreoElectronico(dto.correoElectronico());
        comentario.setComentario(dto.comentario());
        comentario.setPuntuacion(dto.puntuacion());
        blog.agregarComentario(comentario);
        repository.save(blog);
        return comentario.getId();
    }

    @Transactional
    @Override
    public void actualizar(Integer idBlog, BlogUpdateDto dto) {
        Blog blog = repository.findById(idBlog)
                .orElseThrow(() -> new NoEncontradoException("Blog no encontrado"));
        Autor autor = autorRepository.findById(dto.idAutor())
                .orElseThrow(() -> new NoEncontradoException("Autor no encontrado"));
        Tema tema = temaRepository.findById(dto.idTema())
                .orElseThrow(() -> new NoEncontradoException("Tema no encontrado"));

        historialService.guardarHistorial(blog);

        blog.setAutor(autor);
        blog.setTema(tema);
        blog.setTitulo(dto.titulo());
        blog.setContenido(dto.contenido());
        blog.setPeriodicidad(dto.periodicidad());
        blog.setPermiteComentarios(dto.permiteComentarios());
        repository.save(blog);
    }

    @Override
    public BlogDto consultar(Integer id) {
        Blog blog = repository.findById(id)
                .orElseThrow(() -> new NoEncontradoException("Blog no encontrado"));
        AutorDto autorDto = new AutorDto(
                blog.getAutor().getId(),
                blog.getAutor().getPais().getCodigo(),
                blog.getAutor().getNombres(),
                blog.getAutor().getPaterno(),
                blog.getAutor().getMaterno(),
                blog.getAutor().getNacimiento(),
                blog.getAutor().getCorreoElectronico());
        TemaDto temaDto = new TemaDto(blog.getTema().getId(), blog.getTema().getTema());
        List<ComentarioDto> listaComentariosDto = obtenerListaComentarioDto(blog.getComentarios());
        IntSummaryStatistics estadisticas = listaComentariosDto.stream()
                .mapToInt(ComentarioDto::puntuacion)
                .summaryStatistics();
        ResumenPuntuacionDto resumenPuntuacion = new ResumenPuntuacionDto(
                estadisticas.getMax(),
                estadisticas.getMin(),
                estadisticas.getAverage());
        return new BlogDto(
                blog.getId(),
                autorDto,
                temaDto,
                blog.getTitulo(),
                blog.getContenido(),
                blog.getPeriodicidad(),
                blog.getPermiteComentarios(),
                resumenPuntuacion);
    }

    @Override
    public List<ComentarioDto> consultarComentariosDeBlog(Integer idBlog) {
        Blog blog = repository.findById(idBlog)
                .orElseThrow(() -> new NoEncontradoException("Blog no encontrado"));
        return obtenerListaComentarioDto(blog.getComentarios());
    }

    @Override
    public List<HistorialDto> consultarHistorialDeBlog(Integer idBlog) {
        return historialService.obtenerHistorialDeBlog(idBlog);
    }

    private List<ComentarioDto> obtenerListaComentarioDto(List<Comentario> comentarios) {
        return comentarios.stream().map(item -> {
            return new ComentarioDto(
                    item.getId(),
                    item.getBlog().getId(),
                    item.getPais().getNombre(),
                    item.getNombreCompleto(),
                    item.getCorreoElectronico(),
                    item.getComentario(),
                    item.getPuntuacion()
            );
        }).toList();
    }
}
