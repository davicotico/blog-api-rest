package com.davidticona.apiblog.app.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pais")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String codigo;
    private String nombre;
}