package com.example.demo.service;

import com.example.demo.entity.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {
    List<ProductoDTO> listarTodos();
    ProductoDTO obtenerPorId(Long id);
    ProductoDTO crear(ProductoDTO productoDTO);
    ProductoDTO actualizar(Long id, ProductoDTO productoDTO);
    void eliminar(Long id);
}
