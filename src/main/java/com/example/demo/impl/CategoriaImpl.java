package com.example.demo.impl;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.dto.CategoriaDTO;
import com.example.demo.entity.mapper.CategoriaMapper;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class CategoriaImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Override
    public List<CategoriaDTO> listarTodos() {
        return categoriaRepository.findAll()
                .stream()
                .map(categoriaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO obtenerPorId(Long id) {
        return categoriaRepository.findById(id)
                .map(categoriaMapper::toDto)
                .orElse(null);
    }

    @Override
    public CategoriaDTO crear(CategoriaDTO categoriaDTO) {
        Categoria entidad = categoriaMapper.toEntity(categoriaDTO);
        Categoria guardada = categoriaRepository.save(entidad);
        return categoriaMapper.toDto(guardada);
    }

    @Override
    public CategoriaDTO actualizar(Long id, CategoriaDTO categoriaDTO) {
        Optional<Categoria> opt = categoriaRepository.findById(id);
        if (!opt.isPresent()) return null;

        Categoria categoria = opt.get();
        categoria.setNombre(categoriaDTO.getNombre());
        categoria.setDescripcion(categoriaDTO.getDescripcion());

        Categoria actualizada = categoriaRepository.save(categoria);
        return categoriaMapper.toDto(actualizada);
    }

    @Override
    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}
