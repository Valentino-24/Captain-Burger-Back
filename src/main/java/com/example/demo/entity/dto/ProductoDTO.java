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
    // Exponemos la categoría por su id (compatible con el front que usa categoriaId)
    public Long categoriaId;
    // Nombre del campo para URL de imagen coincide con tu entidad
    public String imagenURL;
}
