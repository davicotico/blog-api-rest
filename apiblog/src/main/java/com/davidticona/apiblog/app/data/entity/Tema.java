package com.davidticona.apiblog.app.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tema")
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tema;

    @OneToMany(mappedBy = "tema")
    private List<Blog> blogs;

    @OneToMany(mappedBy = "tema")
    private List<Historial> historiales;
}
