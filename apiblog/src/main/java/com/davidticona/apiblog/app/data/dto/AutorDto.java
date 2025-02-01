package com.davidticona.apiblog.app.data.dto;

import java.time.LocalDate;

public record AutorDto(
        Integer id,
        String codigoPais,
        String nombres,
        String paterno,
        String materno,
        LocalDate nacimiento,
        String correoElectronico
) {}
