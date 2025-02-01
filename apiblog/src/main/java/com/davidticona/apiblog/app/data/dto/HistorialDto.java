package com.davidticona.apiblog.app.data.dto;

import java.time.LocalDateTime;

public record HistorialDto(
        Integer id,
        Integer idBlog,
        TemaDto tema,
        String titulo,
        String contenido,
        String periodicidad,
        Boolean permiteComentarios,
        LocalDateTime fechaActualizacion
) {
}
