package com.example.demo.entity.dto;

import com.example.demo.entity.enums.Rol;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
    public Long id;
    public String nombre;
    public String apellido;
    public String mail;
    public int celular;
    public String contraseña;
    public Rol rol;

}
