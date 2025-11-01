package com.example.demo.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoriaDTO {
    public Long id;
    public String nombre;
    public String descripcion;
}
