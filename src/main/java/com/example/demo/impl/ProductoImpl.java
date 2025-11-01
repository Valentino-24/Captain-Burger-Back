package com.example.demo.impl;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Producto;
import com.example.demo.entity.dto.ProductoDTO;
import com.example.demo.entity.mapper.ProductoMapper;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
public class ProductoImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProductoMapper productoMapper;

    @Override
    public List<ProductoDTO> listarTodos() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .map(productoMapper::toDto)
                .orElse(null);
    }

    @Override
    public ProductoDTO crear(ProductoDTO productoDTO) {
        Producto producto = productoMapper.toEntity(productoDTO);

        // Si vino categoriaId, la asociamos
        if (productoDTO.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaId())
                    .orElse(null);
            producto.setCategoria(categoria);
        }

        Producto guardado = productoRepository.save(producto);
        return productoMapper.toDto(guardado);
    }

    @Override
    public ProductoDTO actualizar(Long id, ProductoDTO productoDTO) {
        Optional<Producto> opt = productoRepository.findById(id);
        if (!opt.isPresent()) return null;

        Producto producto = opt.get();
        // Actualizamos campos permitidos
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setImagenURL(productoDTO.getImagenURL());

        if (productoDTO.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(productoDTO.getCategoriaId()).orElse(null);
            producto.setCategoria(categoria);
        } else {
            producto.setCategoria(null);
        }

        Producto actualizado = productoRepository.save(producto);
        return productoMapper.toDto(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
