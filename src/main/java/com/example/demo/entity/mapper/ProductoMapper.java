package com.example.demo.entity.mapper;

import com.example.demo.entity.Producto;
import com.example.demo.entity.dto.ProductoDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDTO toDto(Producto p) {
        if (p == null) return null;
        Long categoriaId = p.getCategoria() != null ? p.getCategoria().getId() : null;
        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .descripcion(p.getDescripcion())
                .precio(p.getPrecio())
                .stock(p.getStock())
                .categoriaId(categoriaId)
                .imagenURL(p.getImagenURL())
                .disponible(p.isDisponible())
                .build();
    }

    public Producto toEntity(ProductoDTO dto) {
        if (dto == null) return null;
        // Atención: no seteamos la categoria aquí (se hace en el service)
        return Producto.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .imagenURL(dto.getImagenURL())
                .disponible(dto.isDisponible())
                .build();
    }
}
