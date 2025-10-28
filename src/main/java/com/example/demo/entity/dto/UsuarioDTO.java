package com.example.demo.entity.dto;

import com.example.demo.entity.enums.Rol;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class UsuarioDTO {
    public Long id;
    public String nombre;
    public String email;
    public String password;
    public Rol rol;
}
