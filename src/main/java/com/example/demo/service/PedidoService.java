package com.example.demo.service;

import com.example.demo.entity.dto.PedidoDTO;
import com.example.demo.entity.dto.ProductoDTO;

import java.util.List;

public interface PedidoService {
    PedidoDTO crear(PedidoDTO pedidoDTO);
    List<PedidoDTO> listarTodos();
    PedidoDTO obtenerPorId(Long id);
    List<PedidoDTO> listarPorUsuario(Long usuarioId);
    PedidoDTO actualizar(Long id, PedidoDTO pedidoDTO);
    void eliminar(Long id);
    PedidoDTO actualizarEstado(Long id, String estado);
}
