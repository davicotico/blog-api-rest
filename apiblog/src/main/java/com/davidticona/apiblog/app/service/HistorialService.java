package com.davidticona.apiblog.app.service;

import com.davidticona.apiblog.app.data.dto.HistorialDto;
import com.davidticona.apiblog.app.data.entity.Blog;

import java.util.List;

public interface HistorialService {
    void guardarHistorial(Blog blog);
    List<HistorialDto> obtenerHistorialDeBlog(Integer idBlog);
}
