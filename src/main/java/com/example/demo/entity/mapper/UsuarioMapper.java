package com.example.demo.entity.mapper;

import com.example.demo.entity.dto.UsuarioDTO;
import com.example.demo.entity.Usuario;

public class UsuarioMapper {

    public static UsuarioDTO toDTO(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setMail(usuario.getMail());
        dto.setCelular(usuario.getCelular());
        dto.setRol(usuario.getRol());
        return dto;
    }

    public static Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setMail(dto.getMail());
        usuario.setCelular(dto.getCelular());
        usuario.setRol(dto.getRol());
        return usuario;
    }
}
