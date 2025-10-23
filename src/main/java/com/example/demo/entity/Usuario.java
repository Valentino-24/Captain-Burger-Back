package com.example.demo.entity;

import com.example.demo.entity.enums.Rol;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Usuarios")

@NoArgsConstructor
@Getter
@Setter
public class Usuario {
    public Long id;
    public String nombre;
    public String apellido;
    public String mail;
    public int celular;
    public String contraseña;
    public Rol rol;
}
