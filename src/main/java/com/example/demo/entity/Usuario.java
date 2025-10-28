package com.example.demo.entity;

import com.example.demo.entity.enums.Rol;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name="Usuarios")

@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Rol rol = Rol.USUARIO;
}
