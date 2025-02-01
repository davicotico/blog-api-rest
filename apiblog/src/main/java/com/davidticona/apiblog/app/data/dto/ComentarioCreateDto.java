package com.davidticona.apiblog.app.data.dto;

import jakarta.validation.constraints.*;

public record ComentarioCreateDto(

        @NotBlank(message = "El código de país es requerido")
        @Size(min = 2, max = 2, message = "El código de país tiene 2 caracteres")
        String codigoPais,

        @NotBlank(message = "Debe ingresar su nombre para comentar")
        String nombreCompleto,

        @NotBlank(message = "El correo electrónico es requerido")
        @Email(message = "No es un correo electrónico válido")
        String correoElectronico,

        @NotBlank(message = "El comentario es requerido")
        String comentario,

        @NotNull(message = "La puntuación es requerida")
        @Min(value = 1, message = "La puntuación mínima es 1")
        @Max(value = 10, message = "La puntuación máxima es 10")
        Integer puntuacion
) {
}
