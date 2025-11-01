package com.example.demo.entity.mapper;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.dto.CategoriaDTO;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaDTO toDto(Categoria c) {
        if (c == null) return null;
        return CategoriaDTO.builder()
                .id(c.getId())
                .nombre(c.getNombre())
                .descripcion(c.getDescripcion())
                .build();
    }

    public Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) return null;
        return Categoria.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .build();
    }
}
