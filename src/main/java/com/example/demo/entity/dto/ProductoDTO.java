package com.example.demo.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductoDTO {
    public Long id;
    public String nombre;
    public String descripcion;
    public Double precio;
    public Long categoriaId;
    public int stock;
    public String imagenURL;
    public boolean disponible;
}
