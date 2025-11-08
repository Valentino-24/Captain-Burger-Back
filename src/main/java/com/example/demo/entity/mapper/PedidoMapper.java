package com.example.demo.entity.mapper;

import com.example.demo.entity.*;
import com.example.demo.entity.dto.DetallePedidoDTO;
import com.example.demo.entity.dto.PedidoDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoMapper {

    // Pedido -> PedidoDTO
    public PedidoDTO toDto(Pedido p) {
        if (p == null) return null;
        List<DetallePedidoDTO> detalles = p.getDetalles() == null ? null :
                p.getDetalles().stream().map(this::detalleToDto).collect(Collectors.toList());

        return PedidoDTO.builder()
                .id(p.getId())
                .usuarioId(p.getUsuario() != null ? p.getUsuario().getId() : null)
                .telefono(p.getTelefono())
                .direccion(p.getDireccion())
                .metodoPago(p.getMetodoPago())
                .notas(p.getNotas())
                .fecha(p.getFecha())
                .total(p.getTotal())
                .estado(p.getEstado())
                .detalles(detalles)
                .build();
    }

    // PedidoDTO -> Pedido (no set usuario ni detalles aquí, se hacen en el service)
    public Pedido toEntity(PedidoDTO dto) {
        if (dto == null) return null;
        Pedido p = Pedido.builder()
                .telefono(dto.getTelefono())
                .direccion(dto.getDireccion())
                .metodoPago(dto.getMetodoPago())
                .notas(dto.getNotas())
                .total(dto.getTotal())
                .fecha(dto.getFecha())
                .estado(dto.getEstado())
                .build();

        if (dto.getDetalles() != null) {
            List<DetallePedido> detalles = dto.getDetalles().stream().map(this::detalleToEntity).collect(Collectors.toList());
            detalles.forEach(d -> d.setPedido(p));
            p.setDetalles(detalles);
        }

        return p;
    }

    // DetallePedido -> DetallePedidoDTO
    public DetallePedidoDTO detalleToDto(DetallePedido d) {
        if (d == null) return null;
        return DetallePedidoDTO.builder()
                .productoId(d.getProducto() != null ? d.getProducto().getId() : null)
                .productoNombre(d.getProductoNombre())
                .productoImagen(d.getProductoImagen())
                .cantidad(d.getCantidad())
                .precioUnitario(d.getPrecioUnitario())
                .build();
    }

    // DetallePedidoDTO -> DetallePedido (producto se setea en service por id)
    public DetallePedido detalleToEntity(DetallePedidoDTO dto) {
        if (dto == null) return null;
        return DetallePedido.builder()
                .productoNombre(dto.getProductoNombre())
                .productoImagen(dto.getProductoImagen())
                .cantidad(dto.getCantidad())
                .precioUnitario(dto.getPrecioUnitario())
                .build();
    }
}
