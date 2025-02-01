package com.davidticona.apiblog.app.data.dto;

public record BlogDto(
        Integer id,
        AutorDto autor,
        TemaDto tema,
        String titulo,
        String contenido,
        String periodicidad,
        Boolean permiteComentarios,
        ResumenPuntuacionDto resumenPuntuacion
) {
}
