package com.davidticona.apiblog.app.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_pais")
    private Pais pais;

    private String nombres;
    private String paterno;
    private String materno;

    @Column(name = "nacimiento")
    private LocalDate nacimiento;

    @Column(name = "correo_electronico")
    private String correoElectronico;

}
