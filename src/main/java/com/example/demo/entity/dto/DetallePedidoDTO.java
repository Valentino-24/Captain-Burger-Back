package com.example.demo.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DetallePedidoDTO {
    public Long productoId;
    public String productoNombre;
    public String productoImagen;
    public Integer cantidad;
    public Double precioUnitario;
}
