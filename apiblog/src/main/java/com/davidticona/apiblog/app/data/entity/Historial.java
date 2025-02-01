package com.davidticona.apiblog.app.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "historial")
public class Historial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_blog")
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "id_tema")
    private Tema tema;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private String periodicidad;

    @Column(name = "permite_comentarios")
    private Boolean permiteComentarios;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}
