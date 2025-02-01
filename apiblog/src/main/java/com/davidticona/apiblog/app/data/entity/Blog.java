package com.davidticona.apiblog.app.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "id_tema")
    private Tema tema;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private String periodicidad;

    @Column(name = "permite_comentarios")
    private Boolean permiteComentarios;

    @OneToMany(mappedBy = "blog", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Comentario> comentarios = new ArrayList<>();

    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
        comentario.setBlog(this);
    }
}