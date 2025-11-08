package com.example.demo.entity.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PedidoDTO {
    public Long id;
    public Long usuarioId;
    public String telefono;
    public String direccion;
    public String metodoPago;
    public String notas;
    public LocalDateTime fecha;
    public List<DetallePedidoDTO> detalles;
    public Double total;
    public String estado;
}
