package com.example.demo.service;

import com.example.demo.entity.dto.CategoriaDTO;
import java.util.List;

public interface CategoriaService {
    List<CategoriaDTO> listarTodos();
    CategoriaDTO obtenerPorId(Long id);
    CategoriaDTO crear(CategoriaDTO categoriaDTO);
    CategoriaDTO actualizar(Long id, CategoriaDTO categoriaDTO);
    void eliminar(Long id);
}
