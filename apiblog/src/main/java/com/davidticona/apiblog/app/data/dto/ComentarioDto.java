package com.davidticona.apiblog.app.data.dto;

public record ComentarioDto(
        Integer id,
        Integer idBlog,
        String pais,
        String nombreCompleto,
        String correoElectronico,
        String comentario,
        Integer puntuacion
) {
}
