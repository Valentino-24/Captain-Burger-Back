package com.example.demo.entity.mapper;


import com.example.demo.entity.Usuario;
import com.example.demo.entity.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioDTO toDto(Usuario usuario) {
        if (usuario == null) return null;
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .email(usuario.getEmail())
                .password(usuario.getPassword())
                .rol(usuario.getRol())
                .build();
    }

    public Usuario toEntity(UsuarioDTO dto) {
        if (dto == null) return null;
        return Usuario.builder()
                .nombre(dto.getNombre())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .rol(dto.getRol())
                .build();
    }
}