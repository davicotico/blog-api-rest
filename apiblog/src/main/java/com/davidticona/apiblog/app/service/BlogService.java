package com.davidticona.apiblog.app.service;

import com.davidticona.apiblog.app.data.dto.*;

import java.util.List;

public interface BlogService {
    Integer crear(BlogCreateDto dto);
    Integer agregarComentario(Integer idBlog, ComentarioCreateDto dto);
    void actualizar(Integer idBlog, BlogUpdateDto dto);
    BlogDto consultar(Integer id);
    List<ComentarioDto> consultarComentariosDeBlog(Integer idBlog);
    List<HistorialDto> consultarHistorialDeBlog(Integer idBlog);
}
