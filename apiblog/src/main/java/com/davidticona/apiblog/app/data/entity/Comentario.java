package com.davidticona.apiblog.app.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_blog")
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "id_pais")
    private Pais pais;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    private String comentario;

    private Integer puntuacion;
}