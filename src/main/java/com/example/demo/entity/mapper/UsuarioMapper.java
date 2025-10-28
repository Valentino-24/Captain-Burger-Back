package com.example.demo.entity.mapper;

import com.example.demo.entity.dto.UsuarioDTO;
import com.example.demo.entity.Usuario;

public final class UsuarioMapper {

    private UsuarioMapper() {} // utilitario, no instanciable

    public static UsuarioDTO toDto(Usuario u) {
        if (u == null) return null;
        return UsuarioDTO.builder()
                .id(u.getId())
                .nombre(u.getNombre())
                .email(u.getEmail())
                .rol(u.getRol())
                // .password(u.getPassword()) // NO expongas password en respuestas públicas
                .build();
    }

    public static Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;
        return Usuario.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .password(dto.getPassword()) // para creación/actualización sí hace falta
                .rol(dto.getRol())
                .build();
    }
}
